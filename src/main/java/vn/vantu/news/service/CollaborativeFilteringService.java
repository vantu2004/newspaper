package vn.vantu.news.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.vantu.news.repository.UserCategoryRepository;

@Service
public class CollaborativeFilteringService {

	@Autowired
	private UserCategoryRepository userCategoryRepository;

	public double[][] createMatrix() {
	    // Lấy dữ liệu từ bảng
	    List<Object[]> data = userCategoryRepository.findAllUserCategoryData();

	    // Tập hợp các userId và categoryId duy nhất
	    Set<Long> userIds = new HashSet<>();
	    Set<Long> categoryIds = new HashSet<>();

	    for (Object[] row : data) {
	        categoryIds.add(((Number) row[0]).longValue()); // category_id
	        userIds.add(((Number) row[1]).longValue()); // user_id
	    }

	    // Map userId và categoryId thành index
	    List<Long> userIdList = new ArrayList<>(userIds);
	    List<Long> categoryIdList = new ArrayList<>(categoryIds);
	    Map<Long, Integer> userIdIndexMap = new HashMap<>();
	    Map<Long, Integer> categoryIdIndexMap = new HashMap<>();

	    for (int i = 0; i < userIdList.size(); i++) {
	        userIdIndexMap.put(userIdList.get(i), i);
	    }

	    for (int i = 0; i < categoryIdList.size(); i++) {
	        categoryIdIndexMap.put(categoryIdList.get(i), i);
	    }

	    // Tạo ma trận với giá trị mặc định là 0
	    double[][] matrix = new double[userIdList.size()][categoryIdList.size()];

	    // Điền giá trị interaction_score vào ma trận
	    for (Object[] row : data) {
	        Long categoryId = ((Number) row[0]).longValue();
	        Long userId = ((Number) row[1]).longValue();
	        int interactionScore = ((Number) row[2]).intValue();

	        int rowIndex = userIdIndexMap.get(userId);        // Đổi vị trí: userId là hàng
	        int colIndex = categoryIdIndexMap.get(categoryId); // categoryId là cột

	        matrix[rowIndex][colIndex] = interactionScore;
	    }

	    // In ma trận ra console với chỉ mục
	    System.out.print("       "); // Căn chỉnh để khớp tiêu đề cột
	    for (Long categoryId : categoryIdList) {
	        System.out.print(categoryId + " ");
	    }
	    System.out.println();

	    for (int i = 0; i < matrix.length; i++) {
	        System.out.print(userIdList.get(i) + " "); // In userId làm tiêu đề hàng
	        for (int j = 0; j < matrix[i].length; j++) {
	            System.out.print(matrix[i][j] + " ");
	        }
	        System.out.println();
	    }

	    return matrix;
	}

	public List<List<Integer>> collaborativeFiltering (double[][] ratings) {
        int topN = 2; // Number of recommendations per user
        List<List<Integer>> recommendations = generateRecommendations(ratings, topN);

        // Output recommendations
        for (int user = 0; user < recommendations.size(); user++) {
            System.out.println("User " + (user + 1) + " recommendations: " + recommendations.get(user));
        }
        
        return recommendations;
	}
	
	// Compute similarity between two users using cosine similarity
	public static double computeSimilarity(double[] user1, double[] user2) {
		double dotProduct = 0.0, normUser1 = 0.0, normUser2 = 0.0;
		for (int i = 0; i < user1.length; i++) {
			dotProduct += user1[i] * user2[i];
			normUser1 += user1[i] * user1[i];
			normUser2 += user2[i] * user2[i];
		}
		return normUser1 == 0 || normUser2 == 0 ? 0 : dotProduct / (Math.sqrt(normUser1) * Math.sqrt(normUser2));
	}

	// Predict ratings for a specific user
	public static double[] predictRatings(double[][] ratings, int userIndex) {
		int itemCount = ratings[0].length;
		double[] predictions = new double[itemCount];
		double[] userRatings = ratings[userIndex];

		for (int item = 0; item < itemCount; item++) {
			if (userRatings[item] != 0) {
				predictions[item] = userRatings[item]; // Already rated items retain their ratings
				continue;
			}

			double numerator = 0.0, denominator = 0.0;
			for (int otherUser = 0; otherUser < ratings.length; otherUser++) {
				if (otherUser == userIndex)
					continue; // Skip self-similarity
				double similarity = computeSimilarity(userRatings, ratings[otherUser]);
				numerator += similarity * ratings[otherUser][item];
				denominator += Math.abs(similarity);
			}
			predictions[item] = denominator == 0 ? 0 : numerator / denominator;
		}
		return predictions;
	}

	// Generate recommendations for all users
	public static List<List<Integer>> generateRecommendations(double[][] ratings, int topN) {
		List<List<Integer>> recommendations = new ArrayList<>();
		for (int user = 0; user < ratings.length; user++) {
			double[] predictedRatings = predictRatings(ratings, user);
			List<Integer> topItems = getTopNItems(predictedRatings, ratings[user], topN);
			recommendations.add(topItems);
		}
		return recommendations;
	}

	// Get top N items based on predicted ratings
	public static List<Integer> getTopNItems(double[] predictedRatings, double[] originalRatings, int topN) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				(a, b) -> Double.compare(predictedRatings[b], predictedRatings[a]));
		for (int item = 0; item < predictedRatings.length; item++) {
			if (originalRatings[item] == 0) { // Suggest only unrated items
				queue.offer(item);
			}
		}
		List<Integer> topItems = new ArrayList<>();
		for (int i = 0; i < topN && !queue.isEmpty(); i++) {
			topItems.add(queue.poll());
		}
		return topItems;
	}
}
