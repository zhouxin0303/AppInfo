package cn.appsys.pojo;

import java.util.List;

public class PageBean {

        private int pageNo;
        private int pageSize;
        private int totalCount;
        private int totalPageCount;
        private List<AppInfo> Appinfolist;
        public int getPageNo() {
            return pageNo;
        }
        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }
        public int getPageSize() {
            return pageSize;
        }
        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
        public int getTotalCount() {
            return totalCount;
        }
        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
            if(totalCount%pageSize==0){
                totalPageCount=totalCount/pageSize;
            }else{
                totalPageCount=totalCount/pageSize+1;
            }
        }
        public int getTotalPageCount() {
            return totalPageCount;
        }
        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

    public List<AppInfo> getAppinfolist() {
        return Appinfolist;
    }

    public void setAppinfolist(List<AppInfo> appinfolist) {
        Appinfolist = appinfolist;
    }
}
