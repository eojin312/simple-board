/**
 *  postService
 * @type {{upload: PostService.upload, save: PostService.save, renderPagination: PostService.renderPagination, update: PostService.update, linkToDetail: PostService.linkToDetail, delete: PostService.delete, postDto: {img: string, toDto: PostService.postDto.toDto, createdDate: string, contents: string, author: string, title: string, category: string}, getUpdateInfo: PostService.getUpdateInfo, getList: PostService.getList, detail: PostService.detail, renderList: PostService.renderList, config: {PAGES_PER_BLOCK: number}, bindEvent: PostService.bindEvent, validate: PostService.validate}}
 */
PostService = {
    config: {
        PAGES_PER_BLOCK: 5
    },

    /**
     * private renderList list 를 그려준다.
     * @param postList
     */
    renderList: function (postList) {
        let rowHtml = '';
        for (let i = 0; i < postList.length; i++) {
            rowHtml += '<tr key="' + postList[i].id + '">';
            rowHtml += '\t<td>' + '<a href="#" onclick="PostService.linkToDetail(' + postList[i].id + ')">' + postList[i].id + '</a>' + '</td>';
            rowHtml += '\t<td id="title-td-' + postList[i].id + '">' + postList[i].title + ' <span class="comment-count">[9]</span></td>'
            rowHtml += '\t<td>' + postList[i].author + '</td>'
            rowHtml += '\t<td>' + postList[i].category + '</td>'
            rowHtml += '\t<td>' + CommonUtil.localdatetimeToDate(postList[i].createDate) + '</td>'
            rowHtml += '</tr>';
        }
        $('#list-tbody').html(rowHtml);

        var that = this;
        for (let i = 0; i < postList.length; i++) {
            let id = postList[i].id;
            $('#title-td-' + id).on('click', function (event) {
                that.linkToDetail($(event.target).parent().attr('key'));
            });
        }
    },

    linkToDetail: function (id) {
        window.location.href = '/posts/' + id;
    },

    getList: function (_page) {
        if (_page == undefined || _page == null) {
            _page = 0;
        }
        $.ajax({
            url: '/api/posts',
            type: 'GET',
            context: window.PostService,
            data: {
                page: _page
            }
        }).done(function (page) {
            let postList = page.content;
            if (postList == undefined || postList == null) {
                $('#list-tbody').html('<tr><td colspan="10">글이 아무것도 등록되어있지 않습니다.</td></tr>');
                return;
            }
            this.renderList(postList);
            this.renderPagination(page.totalPages, page.pageable.pageNumber, page.size, page.totalElements, '.pagination-area2');

        }).fail(function (res) {
            alert("서버에 문제가 발생했습니다.");
            console.log(res);
        });
    },

    /**
     * private renderPagination
     * @param totalPage 전체 페이지 수
     * @param _crrentPage 현재 페이지 번호
     * @param size 한 페이지 당 게시글의 수
     * @param totalElements 총 게시글의 수
     * @param _areaClassId 페
     * 이지네이션이 그려질 영역의 class id, default 는 .pagination-area
     */
    renderPagination: function (totalPage, _crrentPage, size, totalElements, _areaClassId) {

        let areaClassId = (_areaClassId == undefined || _areaClassId == null) ? '.pagination-area' : _areaClassId;
        let crrentPage = (_crrentPage < 1) ? 1 : _crrentPage;
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

        if (isFirstBlock == false) paginationString += '<span onclick="PostService.getList(' + firstPageOfPrevBlock + ')" style="cursor:pointer;" >[이전]</span>';


        for (let i = 0, j = startPageOfBlock; i < loopCount; i++, j++) {
            let styleBold = (crrentPage == j) ? 'font-weight: bold' : '';
            paginationString += '<span onclick="PostService.getList(' + j + ')" style="cursor:pointer; ' + styleBold + '">' + ' [' + j + ']</span>';
        }
        if (isLastBlock == false) paginationString += '<span onclick="PostService.getList(' + firstPageOfNextBlock + ')" style="cursor:pointer;" >[다음]</span>';
        $(areaClassId).html(paginationString);
    },
    save: function () {
        this.postDto.toDto();

        if (this.validate() == false) {
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/posts',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.postDto)
        }).done(function (id) {
            alert('정상적으로 글이 등록되었습니다');
            window.location.href = '/posts/' + id;
        })
    },
    bindEvent: function () {
        var _this = this;
        $('#create_button').on('click', this.save);
    },
    validate: function () {
        if (this.postDto.title == '') {
            alert('제목을 입력해주세요')
            $('#title').focus();
            return false;
        }
        if (this.postDto.contents == '') {
            alert('글을 입력해주세요')
            $('#post-contents').focus();
            return false;
        }

    },
    upload: function () {
        let form = $('#upload-form')[0];
        let data = new FormData(form);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            url: '/api/upload',
            processData: false,
            contentType: false,
            cache: false,
            success: function (fileName) {
                $('#profile-image').attr('src', '/api/download?file-name=' + fileName);
                $('#file-name').val(fileName);
            }
        })
    },
    update: function () {
        this.postDto.toDto();
        if (this.validate() == false) {
            return;
        }
        $.ajax({
            type: 'PUT',
            url: '/api/posts',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(this.postDto)
        }).done(function (post) {
            alert('정상적으로 변경이 완료되었습니다')
            window.location.href = '/posts/' + post.id;
        })
    },
    getUpdateInfo: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/posts/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (post) {
            $('#title').val(post.title);
            $('#category').val(post.category);
            $('#post-contents').val(post.contents);
            $('#file-name').attr("src", '/api/download?file-name=' + post.img);
        });
    },
    delete: function (postId) {
        $.ajax({
            type: 'DELETE',
            url: '/api/posts/' + postId,
            dataType: 'json',
            contentType: 'application/json',
        }).done(function () {
            alert('게시물이 삭제되었습니다.');
            window.location.href = '/posts';
        })
    },
    postDto: {
        title: '',
        contents: '',
        author: '',
        category: '',
        createdDate: '',
        img: '',
        toDto: function () {
            if ($('#id') != undefined) {
                this.id = $.trim($('#id').text());
            }
            this.title = $.trim($('#title').val());
            this.contents = $.trim($('#post-contents').val());
            this.author = $.trim($('#author').val());
            this.category = $.trim($('#category').val());
            this.createdDate = $.trim($('#createdDate').val());
            this.img = $.trim($('#file-name').val());
        }
    }
}