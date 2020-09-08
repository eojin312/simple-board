/**
 * userService
 * @type {{upload: UserService.upload, getDetail: UserService.getDetail, save: UserService.save, update: UserService.update, renderPagination: UserService.renderPagination, getUserList: UserService.getUserList, delete: UserService.delete, userDto: {password: string, toDto: UserService.userDto.toDto, role: string, gender: string, name: string, profileImage: string, email: string, username: string}, isEmptyAndAlert(*, *): (boolean|undefined), search: UserService.search, getUpdateInfo: UserService.getUpdateInfo, renderList: UserService.renderList, config: {PAGES_PER_BLOCK: number}, bindEvent: UserService.bindEvent, constantValue: {MIN_LOGIN_PASSWORD: number, MIN_LOGIN_ID: number}, validate: UserService.validate}}
 */
UserService = {
    config: {
        PAGES_PER_BLOCK: 5
    },
    constantValue: {
        MIN_LOGIN_ID: 3,
        MIN_LOGIN_PASSWORD: 6
    },
    userDto: {
        name: '',
        email: '',
        username: '',
        password: '',
        gender: '',
        profileImage: '',
        role: 'MEMBER',
        toDto: function () {
            if ($('#id') != undefined) {
                this.id = $.trim($('#id').text());
            }
            this.name = $.trim($('#name').val());
            this.email = $.trim($('#email').val());
            this.username = $.trim($('#username').val());
            this.password = $.trim($('#password').val());
            this.birthYear = $.trim($('#birth-year').val());
            this.gender = $.trim($('#gender').val());
            this.profileImage = $.trim($('#file-name').val());
            this.role = $.trim($('#role').val());
        },
    },
    bindEvent: function () {
        var _this = this;
        $('#create_button').on('click', this.save);
    },
    save: function () {
        this.userDto.toDto();

        if (this.validate() == false) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/api/users',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.userDto)
        }).done(function (user) {
            alert('정상적으로 회원이 등록되었습니다');
            window.location.href = '/users/detail/' + user.id;
        }).fail(function (errorResponse) {
            if (errorResponse.message == undefined) {
                alert(errorResponse.responseJSON.errors[0].defaultMessage);
            } else {
                alert(errorResponse.message);
            }
        });
    },
    update: function () {
        this.userDto.toDto();

        if (this.validate() == false) {
            return;
        }
        $.ajax({
            type: 'PUT',
            url: '/api/users/',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(this.userDto)
        }).done(function (user) {
            alert("정상적으로 변경이 완료되었습니다.")
            location.href = '/users/detail/' + user.id;
        }).fail(function (errorResponse) {
            if (errorResponse.message == undefined) {
                alert(errorResponse.responseJSON.errors[0].defaultMessage);
            } else {
                alert(errorResponse.message);
            }
        })
    },
    validate: function () {
        if (this.userDto.name == '') {
            alert('이름을 입력해주세요');
            $('#name').focus();
            return false;
        }
        if (this.isEmptyAndAlert('email', '이메일') == false) {
            return false;
        }
        if (this.userDto.username == '') {
            alert('아이디를 입력해주세요');
            $('#username').focus();
            return false;
        }
        if (this.userDto.username.length < this.constantValue.MIN_LOGIN_ID) {
            alert(`아이디는 최소 ${this.constantValue.MIN_LOGIN_ID}자 이상 입력해주세요`);
            $('#username').focus();
            return false;
        }
        if (this.userDto.password == '') {
            alert('비밀번호를 입력해주세요');
            $('#password').focus();
            return false;
        }
        this.isEmptyAndAlert('email', '이메일');
        if (this.userDto.password.length < this.constantValue.MIN_LOGIN_PASSWORD) {
            alert(`비밀번호는 최소 ${this.constantValue.MIN_LOGIN_PASSWORD}자 이상 입력해주세요`);
            $('#password').focus();
            return false;
        }
        if ($('#password').val() != $('#password-check').val()) {
            alert('비밀번호가 일치하지않아요!');
            $('#password').focus();
            return false;
        }
    },
    isEmptyAndAlert(target, label) {
        if (this.userDto[target] == '') {
            alert(`${label}을 입력해주세요`);
            $(`#${target}`).focus();
            return false;
        }
    },
    getUserList: function (_page, searchType, searchKeyword) {
        if (_page == undefined || _page == null) {
            _page = 0;
        }
        $.ajax({
            url: "/api/users",
            context: window.UserService,
            data: {
                page: _page,
                searchType: searchType,
                searchKeyword: searchKeyword
            }
        }).done(function (page) {
            let userList = page.content;
            if (userList == undefined || userList == null) {
                $('#list-tbody').html('<tr><td colspan="6">회원이 아무도 등록되어있지 않습니다.</td></tr>');
                return;
            }
            this.renderList(userList);
            this.renderPagination(page.totalPages, page.pageable.pageNumber, page.size, page.totalElements, '.pagination-area');
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
     * @param _areaClassId 페이지네이션이 그려질 영역의 class id, default 는 .pagination-area
     */
    renderPagination: function (totalPage, _crrentPage, size, totalElements, _areaClassId) {

        let areaClassId = (_areaClassId === undefined || _areaClassId == null) ? '.pagination-area' : _areaClassId;
        let crrentPage = (_crrentPage < 1) ? 1
            : _crrentPage;
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
        $(areaClassId).html(paginationString);
    },
    search: function (event) {
        let searchType = $('#search-type').val();
        let searchKeyword = $('#search-keyword').val();
        if ($.trim(searchKeyword) == '') {
            alert('검색어를 입력해주세요');
            event.preventDefault();
            return;
        }
        this.getUserList(null, searchType, searchKeyword);
    },
    renderList: function (userList) {
        let rowHtml = '';

        for (let i = 0; i < userList.length; i++) {
            let genderString = (userList[i].gender == 'M') ? '남' : '여';
            rowHtml += '<tr>';
            rowHtml += '\t<td>' + userList[i].id + '</td>';
            rowHtml += '\t<td>' + userList[i].username + '</td>';
            rowHtml += '\t<td>' + '<a href="/users/detail/' + userList[i].id + '">' + userList[i].name + '</a>' + '</td>';
            rowHtml += '\t<td>' + userList[i].email + '</td>';
            rowHtml += '\t<td>' + userList[i].birthYear + '년</td>';
            rowHtml += '\t<td>' + genderString + '</td>';
            rowHtml += '</tr>';
        }
        $('#list-tbody').html(rowHtml);
    },
    getDetail: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (user) {
            $('#name').html(user.name);
            $('#username').html(user.username);
            $('#email').html(user.email);
            $('#birth-year').html(user.birthYear);
            $('#gender').html(user.gender);
            $('#profile-image').attr("src", '/api/download?file-name=' + user.profileImage)
        });
    },
    getUpdateInfo: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (user) {
            $('#name').val(user.name);
            $('#username').val(user.username);
            $('#password').val(user.password);
            $('#email').val(user.email);
            $('#birth-year').val(user.birthYear);
            $('#gender').val(user.gender);
            $('#profile-image').attr("src", '/api/download?file-name=' + user.profileImage)
        })
        ;
    },
    delete: function () {
        $.ajax({
            type: 'DELETE',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function () {
            alert('회원이 삭제되었습니다.');
            window.location.href = '/users'
        })
    },
    upload: function (event) {
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
    }
};

