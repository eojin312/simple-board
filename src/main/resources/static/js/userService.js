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
            rowHtml += '\t<td>' + '<a href="/users/detail/' + userList[i].id + '">' + userList[i].name + '</a>' + '</td>';
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
    },
    getDetail: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (res) {
            $('#name').html(res.name);
            $('#loginId').html(res.loginId);
            $('#email').html(res.email);
            $('#birthYear').html(res.birthYear);
            $('#gender').html(res.gender);
        })
        ;
    },
    getUpdateInfo: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (res) {
            $('#name').val(res.name);
            $('#loginId').val(res.loginId);
            $('#loginPassword').val(res.loginPassword);
            $('#email').val(res.email);
            $('#birthYear').val(res.birthYear);
            $('#gender').val(res.gender);
        })
        ;
    },
    constantValue: {
        MIN_LOGIN_ID: 3,
        MIN_LOGIN_PASSWORD: 6
    },
    userDto: {

        name: '',
        email: '',
        loginId: '',
        loginPassword: '',
        gender: '',
        profileImage: '',
        toDto: function () {
            this.name = $.trim($('#name').val());
            this.email = $.trim($('#email').val());
            this.loginId = $.trim($('#loginId').val());
            this.loginPassword = $.trim($('#loginPassword').val());
            this.birthYear = $.trim($('#birthYear').val());
            this.gender = $.trim($('#gender').val());
            this.profileImage = $.trim($('#profileImage').val());
        }
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
        }).done(function (res) {
            location.href = '/users/detail/' + res.id;
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
        if (this.userDto.loginId == '') {
            alert('아이디를 입력해주세요');
            $('#loginId').focus();
            return false;
        }
        if (this.userDto.loginId.length < this.constantValue.MIN_LOGIN_ID) {
            alert(`아이디는 최소 ${this.constantValue.MIN_LOGIN_ID}자 이상 입력해주세요`);
            $('#loginId').focus();
            return false;
        }
        if (this.userDto.loginPassword == '') {
            alert('비밀번호를 입력해주세요');
            $('#loginPassword').focus();
            return false;
        }
        this.isEmptyAndAlert('email', '이메일');
        if (this.userDto.loginPassword.length < this.constantValue.MIN_LOGIN_PASSWORD) {
            alert(`비밀번호는 최소 ${this.constantValue.MIN_LOGIN_PASSWORD}자 이상 입력해주세요`);
            $('#loginPassword').focus();
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
    }
};

