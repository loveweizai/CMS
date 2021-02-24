package net.mingsoft.cms.model;

public class ProjectInfo {

	/**
	 * 
	 {
		"engineeringunit": "白鹤滩施工局",
		"areaid": "530622",
		"provinceid": "530000",
		"countryid": "1",
		"country": "中国",
		"cityid": "530600",
		"city": "昭通市",
		"id": "297edff84938925401494b9cd116118a",
		"area": "巧家县",
		"address": "金沙江白鹤滩",
		"name": "白鹤滩水电站（白鹤滩施工局）",
		"province": "云南省",
		"projectsummary": "白鹤滩水电站位于金沙江下游四川省宁南县和云南省巧家县，距巧家县城45km，距昆明约260km，至重庆、成都、贵阳均在400km左右，至华东地区上海的直线距离为1850km。电站上接乌东德梯级，下邻溪洛渡梯级，距溪洛渡水电站195km。\r\n白鹤滩水电站的开发任务以发电为主，电站正常蓄水位为825.0m，水库总库容206.27亿m3。枢纽工程主要由混凝土双曲拱坝、二道坝及水垫塘、泄洪洞、引水发电系统等建筑物组成。混凝土双曲拱坝坝顶高程834.0m，最大坝高289.0m，坝身布置有6孔泄洪表孔和7孔泄洪深孔；泄洪洞共 3条，均布置在左岸；电站总装机容量16000MW，左、右岸地下厂房各布置 8台单机容量1000MW 的水轮发电机组。 \r\n本合同工程主要包括高程834～600m坝基及拱肩槽边坡工程（含右岸中上部坝肩处理工程、建基面处理工程），右岸坝基（肩）帷幕灌浆洞、截渗洞、排水洞工程，下游834～600m高程水垫塘及上部边坡开挖及支护工程，右岸进水口834～734m高程边坡开挖及支护工程，右岸马脖子780~734m高程边坡开挖及支护工程，上游围堰工程。\r\n",
		"enddate": "2016-02-29",
		"begindate": "2014-02-01",
		"lastdate": "2019-05-09"
	}
	 */
	
	private String area;
	private String address;
	private String engineeringunit;
	private String name;
	private String projectsummary;
	private String province;
	private String enddate;
	private String begindate;
	private String city;
	private String country;
	
	private String areaid;
	private String id;
	private String cityid;
	private String provinceid;
	private String countryid;
	private String lastdate;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEngineeringunit() {
		return engineeringunit;
	}
	public void setEngineeringunit(String engineeringunit) {
		this.engineeringunit = engineeringunit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectsummary() {
		return projectsummary;
	}
	public void setProjectsummary(String projectsummary) {
		this.projectsummary = projectsummary;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
}
