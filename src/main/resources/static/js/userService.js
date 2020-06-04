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
        loginId: '',
        loginPassword: '',
        gender: '',
        profileImage: '',
        toDto: function () {
            if ($('#id') != undefined) {
                this.id = $.trim($('#id').text());
            }
            this.name = $.trim($('#name').val());
            this.email = $.trim($('#email').val());
            this.loginId = $.trim($('#login-id').val());
            this.loginPassword = $.trim($('#login-password').val());
            this.birthYear = $.trim($('#birth-year').val());
            this.gender = $.trim($('#gender').val());
            this.profileImage = $.trim($('#file-name').val());
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
        }).done(function (id) {
            alert('정상적으로 회원이 등록되었습니다');
            window.location.href = '/users/detail/' + id; // TODO : 회원상세페이지로 이동으로 변겨애ㅑ ㅇㅇs
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
        if (this.userDto.loginId == '') {
            alert('아이디를 입력해주세요');
            $('#login-id').focus();
            return false;
        }
        if (this.userDto.loginId.length < this.constantValue.MIN_LOGIN_ID) {
            alert(`아이디는 최소 ${this.constantValue.MIN_LOGIN_ID}자 이상 입력해주세요`);
            $('#login-id').focus();
            return false;
        }
        if (this.userDto.loginPassword == '') {
            alert('비밀번호를 입력해주세요');
            $('#login-password').focus();
            return false;
        }
        this.isEmptyAndAlert('email', '이메일');
        if (this.userDto.loginPassword.length < this.constantValue.MIN_LOGIN_PASSWORD) {
            alert(`비밀번호는 최소 ${this.constantValue.MIN_LOGIN_PASSWORD}자 이상 입력해주세요`);
            $('#login-password').focus();
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
        let searchType = $('#search-type').val();
        let searchKeyword = $('#search-keyword').val();

        $.ajax({
            url: "/api/users",
            context: window.UserService,
            data: {
                searchType: searchType,
                searchKeyword: searchKeyword
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
    getDetail: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (user) {
            $('#name').html(user.name);
            $('#login-id').html(user.loginId);
            $('#email').html(user.email);
            $('#birth-year').html(user.birthYear);
            $('#gender').html(user.gender);
            $('#profile-image').attr("src", '/api/download?file-name=' + user.profileImage)
        })
        ;
    },
    getUpdateInfo: function (id) {
        $.ajax({
            type: 'GET',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json'
        }).done(function (user) {
            $('#name').val(user.name);
            $('#login-id').val(user.loginId);
            $('#login-password').val(user.loginPassword);
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
    upload: function upload(event) {
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

