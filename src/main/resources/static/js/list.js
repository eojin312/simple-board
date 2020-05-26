UserService = {
    config: {
        PAGES_PER_BLOCK: 5
    }
    ,
    getUserList: function (_page) {
        if (_page == undefined || _page == null) {
            _page = 0;
        }
        $.ajax({
            url: "/api/users",
            context: window.UserService,
            data: {
                page: _page
            }
        })
            .done(function (res) {
                let userList = res.content;
                if (userList == undefined || userList == null) {
                    $('#list-tbody').html('<tr><td colspan="6">회원이 아무도 등록되어있지 않습니다.</td></tr>');
                    return;
                }
                this.renderPagination(res.totalPages, res.pageable.pageNumber, res.size, res.totalElements);
                this.renderList(userList);
            })
        ;
    },

    renderPagination: function (totalPage, _crrentPage, size, totalElements) {
        let crrentPage = _crrentPage + 1;
        let currentBlock = Math.ceil(crrentPage / this.config.PAGES_PER_BLOCK);
        let startPageOfBlock = Math.ceil((currentBlock - 1) * this.config.PAGES_PER_BLOCK) + 1;
        let totalBlock = Math.ceil(totalElements / size / this.config.PAGES_PER_BLOCK);
        let isLastBlock = (currentBlock == totalBlock);
        let isFirstBlock = (currentBlock == 1);
        let paginationString = '';
        let lastPageOfBlock = currentBlock * this.config.PAGES_PER_BLOCK;
        let firstPageOfNextBlock = lastPageOfBlock + 1;
        let firstPageOfPrevBlock = (currentBlock - 2) * this.config.PAGES_PER_BLOCK + 1;

        // 마지막 블록이면
        // let loopCount = 16 % 5 = 1
        // 아니면
        // loopCount = PAGES_PER_BLOCK

        let loopCount = (isLastBlock) ? (totalPage % this.config.PAGES_PER_BLOCK) : this.config.PAGES_PER_BLOCK;
        let lessThanMinBlock = (totalElements <= (this.config.PAGES_PER_BLOCK - 1) * size);
        loopCount = (isFirstBlock && lessThanMinBlock) ? totalPage : loopCount;

        if (isFirstBlock == false) paginationString += '<span onclick="UserService.getUserList(' + firstPageOfPrevBlock + ')" style="cursor:pointer;" >[이전]</span>';


        for (let i = 0, j = startPageOfBlock; i < loopCount; i++, j++) {
            let styleBold = (crrentPage == j) ? 'font-weight: bold' : '';
            paginationString += '<span onclick="UserService.getUserList(' + j + ')" style="cursor:pointer; ' + styleBold + '">' + ' [' + j + ']</span>';
        }
        if (isLastBlock == false) paginationString += '<span onclick="UserService.getUserList(' + firstPageOfNextBlock + ')" style="cursor:pointer;" >[다음]</span>';
        $('.pagination-area').html(paginationString);
    },
    search: function () {
        let searchKeyword = $('#search_keyword').val();

        $.ajax({
            url: "/api/users",
            context: window.UserService,
            data: {
                search_keyword: searchKeyword
            }
        })
            .done(function (res) {
                let userList = res.content;
                if (userList == undefined || userList == null) {
                    $('#list-tbody').html('<tr><td colspan="6">검색 결과가 없습니다</td></tr>');
                    return;
                }
                this.renderList(userList);
            })
        ;
    },
    renderList: function (userList) {
        let rowHtml = '';

        for (let i = 0; i < userList.length; i++) {
            let genderString = (userList[i].gender == 'M') ? '남' : '여';
            rowHtml += '<tr>';
            rowHtml += '\t<td>' + userList[i].id + '</td>';
            rowHtml += '\t<td>' + userList[i].loginId + '</td>';
            rowHtml += '\t<td>' + '<a href="/users/detail/"+'
            {
                userList.id
            }
            '+>' + userList[i].name + '</a>' + '</td>';
            rowHtml += '\t<td>' + userList[i].email + '</td>';
            rowHtml += '\t<td>' + userList[i].birthYear + '년</td>';
            rowHtml += '\t<td>' + genderString + '</td>';
            rowHtml += '</tr>';
        }
        $('#list-tbody').html(rowHtml);
    },
    search: function (target) {
        let word = target.value;
        let encodeWord = encodeURI(word);

        $.ajax({
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                $('#search-result-area').empty();
                let checkKeyWord = $('#search_keyword').val();
                let searchRowHtml = '';
                if (checkKeyWord.length > 0 && data.dataSearch.content.length > 0) {
                    for (i = 0; i < data.dataSearch.content.length; i++) {
                        $('#search-tbody').append(
                            "<li class='schoolList' value='" + data.dataSearch.content[i].name + "' data-input='" + data.dataSearch.content[i].schoolName + ">" +
                            "<a href='javascript:void(0);'>" + data.dataSearch.content[i].schoolName + "</a>" + "</li>");
                    }
                    ;
                }
            }
        });
    }
};
$(document).ready(function () {
    UserService.getUserList();
});
