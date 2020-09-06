package hachi.simpleboard.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ListResponseDto<T> {
    private Page<T> page;
    private String searchType;
    private String searchKeyword;

    @Builder
    public ListResponseDto(Page<T> page, String searchType, String searchKeyword) {
        this.page = page;
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
    }
}
