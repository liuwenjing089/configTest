package com.andon.bean;

import java.util.List;

public class BaseQEntity {
	/******************************************************************
	*isFileName  ----链接地址
			totalRows    ----记录总数
	        totalPages   ----页数
	        perPageRows  ----每页数量
	        currentPage ----当前�?
		    showTotal   ----是否显示总数�?
		    showPages   ----是否分页
		    orderby   ----用什么列排序
		    asc   ------升序还是降序
		    showAllPages ---是否用下拉列表显示所有页面以供跳转�?
		    strUnit     ----计数单位(�?
		    showMaxPerPage  ----是否显示每页信息量�?项框
		    ispaged  --是否把page做为参数如：url&page=1;
	 *****************************************************************/
	//链接地址
	private String sFileName;
	
	//总的记录数
	protected int totalRows = 0;

	//当前页面
	protected int currentPage = 1;

	//总的页面数?
	protected int totalPages = 0;

	//每一页显示的记录数?
	protected int perPageRows = 10;

	//是不是实现分页?
	protected boolean showPages = true;
	
	//是否用下拉列表显示所有页面以供跳转
	private boolean showAllPages=true;
	
	//计数单位
	private String strUnit="条";
	
	//是否是末尾
	private boolean isPaged=true;
	
	//是否显示每页信息量框
	private boolean showMaxPerPage=true;
	
	//从第几行开始查询
	private int startRow = 0;
	
	//查询返回的List
	private List<Object> lst;
	
	//要查询的对象
	private Object obj;
	

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<Object> getLst() {
		return lst;
	}

	public void setLst(List<Object> lst) {
		this.lst = lst;
	}
	public String getsFileName() {
		return sFileName;
	}

	public void setsFileName(String sFileName) {
		this.sFileName = sFileName;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPerPageRows() {
		return perPageRows;
	}

	public void setPerPageRows(int perPageRows) {
		this.perPageRows = perPageRows;
	}

	public boolean isShowPages() {
		return showPages;
	}

	public void setShowPages(boolean showPages) {
		this.showPages = showPages;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		//计算出现在的总的页数
		this.totalPages =  (this.totalRows - 1) / this.perPageRows + 1;
		if (this.currentPage > this.totalPages) {
			this.currentPage = 1;
		}
	}

	public String getSFileName() {
		return sFileName;
	}

	public void setSFileName(String fileName) {
		sFileName = fileName;
	}

	public boolean isShowAllPages() {
		return showAllPages;
	}

	public void setShowAllPages(boolean showAllPages) {
		this.showAllPages = showAllPages;
	}

	public boolean isShowMaxPerPage() {
		return showMaxPerPage;
	}

	public void setShowMaxPerPage(boolean showMaxPerPage) {
		this.showMaxPerPage = showMaxPerPage;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}

	public boolean getIsPaged() {
		return isPaged;
	}

	public void setIsPaged(boolean isPaged) {
		this.isPaged = isPaged;
	}
}
