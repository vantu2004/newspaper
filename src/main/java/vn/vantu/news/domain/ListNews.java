package vn.vantu.news.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListNews {
	List<News> tinMoi = new ArrayList<>();
	
	List<News> theGioi = new ArrayList<>();
	List<News> thoiSu = new ArrayList<>();
	List<News> kinhDoanh = new ArrayList<>();
	List<News> startup = new ArrayList<>();
	List<News> giaiTri = new ArrayList<>();
	List<News> theThao = new ArrayList<>();
	List<News> phapLuat = new ArrayList<>();
	List<News> giaoDuc = new ArrayList<>();
	List<News> sucKhoe = new ArrayList<>();
	List<News> doiSong = new ArrayList<>();
	List<News> duLich = new ArrayList<>();
	List<News> khoaHoc = new ArrayList<>();
	List<News> soHoa = new ArrayList<>();
	List<News> xe = new ArrayList<>();
	List<News> yKien = new ArrayList<>();
	List<News> tamSu = new ArrayList<>();
	
	Map<Long, String> categoryName = new HashMap<>();

	public ListNews()
	{
		setCategoryName();
	}
	
	
	
	public Map<Long, String> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(Map<Long, String> categoryName) {
		this.categoryName = categoryName;
	}



	public List<News> getTinMoi() {
		return tinMoi;
	}

	public void setTinMoi(List<News> tinMoi) {
		this.tinMoi = tinMoi;
	}

	public List<News> getTheGioi() {
		return theGioi;
	}

	public void setTheGioi(List<News> theGioi) {
		this.theGioi = theGioi;
	}

	public List<News> getThoiSu() {
		return thoiSu;
	}

	public void setThoiSu(List<News> thoiSu) {
		this.thoiSu = thoiSu;
	}

	public List<News> getKinhDoanh() {
		return kinhDoanh;
	}

	public void setKinhDoanh(List<News> kinhDoanh) {
		this.kinhDoanh = kinhDoanh;
	}

	public List<News> getStartup() {
		return startup;
	}

	public void setStartup(List<News> startup) {
		this.startup = startup;
	}

	public List<News> getGiaiTri() {
		return giaiTri;
	}

	public void setGiaiTri(List<News> giaiTri) {
		this.giaiTri = giaiTri;
	}

	public List<News> getTheThao() {
		return theThao;
	}

	public void setTheThao(List<News> theThao) {
		this.theThao = theThao;
	}

	public List<News> getPhapLuat() {
		return phapLuat;
	}

	public void setPhapLuat(List<News> phapLuat) {
		this.phapLuat = phapLuat;
	}

	public List<News> getGiaoDuc() {
		return giaoDuc;
	}

	public void setGiaoDuc(List<News> giaoDuc) {
		this.giaoDuc = giaoDuc;
	}

	public List<News> getSucKhoe() {
		return sucKhoe;
	}

	public void setSucKhoe(List<News> sucKhoe) {
		this.sucKhoe = sucKhoe;
	}

	public List<News> getDoiSong() {
		return doiSong;
	}

	public void setDoiSong(List<News> doiSong) {
		this.doiSong = doiSong;
	}

	public List<News> getDuLich() {
		return duLich;
	}

	public void setDuLich(List<News> duLich) {
		this.duLich = duLich;
	}

	public List<News> getKhoaHoc() {
		return khoaHoc;
	}

	public void setKhoaHoc(List<News> khoaHoc) {
		this.khoaHoc = khoaHoc;
	}

	public List<News> getSoHoa() {
		return soHoa;
	}

	public void setSoHoa(List<News> soHoa) {
		this.soHoa = soHoa;
	}

	public List<News> getXe() {
		return xe;
	}

	public void setXe(List<News> xe) {
		this.xe = xe;
	}

	public List<News> getyKien() {
		return yKien;
	}

	public void setyKien(List<News> yKien) {
		this.yKien = yKien;
	}

	public List<News> getTamSu() {
		return tamSu;
	}

	public void setTamSu(List<News> tamSu) {
		this.tamSu = tamSu;
	}
	
	private void setCategoryName()
	{
		categoryName.put(Long.valueOf(1), "Thế giới");
		categoryName.put(Long.valueOf(2), "Thời sự");
		categoryName.put(Long.valueOf(3), "Kinh doanh");
		categoryName.put(Long.valueOf(4), "Startup");
		categoryName.put(Long.valueOf(5), "Thể thao");
		categoryName.put(Long.valueOf(6), "Giải trí");
		categoryName.put(Long.valueOf(7), "Pháp luật");
		categoryName.put(Long.valueOf(8), "Giáo dục");
		categoryName.put(Long.valueOf(9), "Sức khỏe");
		categoryName.put(Long.valueOf(10), "Đời sống");
		categoryName.put(Long.valueOf(11), "Du lịch");
		categoryName.put(Long.valueOf(12), "Khoa học");
		categoryName.put(Long.valueOf(13), "Số hóa");
		categoryName.put(Long.valueOf(14), "Xe");
		categoryName.put(Long.valueOf(15), "Ý kiến");
		categoryName.put(Long.valueOf(16), "Tâm sự");
	}
}
