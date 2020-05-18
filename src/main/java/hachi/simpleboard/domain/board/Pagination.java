package hachi.simpleboard.domain.board;

/*
    페이징 구현
  */
public class Pagination {

    // 페이지 구간 내 페이지 갯수
    private int pageCountPerBlock;

    // 전체 게시글 수
    private int totalCount;

    // 페이지 당 게시글 개수
    private int articleCountPerPage;

    public Pagination(int pageCountPerBlock, int totalCount, int articleCountPerPage) {
        this.pageCountPerBlock = pageCountPerBlock;
        this.totalCount = totalCount;
        this.articleCountPerPage = articleCountPerPage;
    }

    // ( 현재 페이지 - 1 ) / 블록 당 페이지 개수
    public int getBlockNo(int pageNo) {
        return (int) Math.floor((pageNo - 1) / pageCountPerBlock);
    }

    public int getStartPageNo(int pageNo) {
        return (this.getBlockNo(pageNo) * this.pageCountPerBlock) + 1;
    }

    public int getLastPageNo() {
        double temp = (double) this.totalCount / (double) this.pageCountPerBlock;
        return (int) Math.ceil(temp);
    }

    public int getStartLimit(int pageNo) {
        return (pageNo - 1) * this.articleCountPerPage;
    }
}
