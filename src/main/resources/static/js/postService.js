PostService = {
    renderList: function (postList) {
        let rowHtml = '';
        for (let i = 0; i < postList.length; i++) {
            rowHtml += '<tr>';
            rowHtml += '\t<td>' + '<a href="/post/detail/' + postList[i].id + '">' + 'postList[i].id' + '</a>' + '</td>';
            rowHtml += '\t<td>' + 'postList[i].title' + '</td>'
            rowHtml += '\t<td>' + 'postList[i].author' + '</td>'
            rowHtml += '\t<td>' + 'postList[i].category' + '</td>'
            rowHtml += '\t<td>' + 'postList[i].createdDate' + '</td>'
            rowHtml += '</tr>';
        }
        $('#list-tbody').html(rowHtml);
    },
    save: function () {
        this.userDto.toDto();

        if (this.validate() == false) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/api/posts',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.userDto)
        }).done(function (id) {
            alert('정상적으로 글이 등록되었습니다');
            window.location.href = '/posts/detail/' + id;
        }).fail(function (errorResponse) {
            if (errorResponse.message == undefined) {
                alert(errorResponse.responseJSON.errors[0].defaultMessage);
            } else {
                alert(errorResponse.message);
            }
        });
    },
    bindEvent: function () {
        var _this = this;
        $('#create_button').on('click', this.save);
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
    },
    postDto: {
        title: '',
        contents: '',
        author: '',
        category: '',
        img: '',
        toDto: function () {
            if ($('#id') != undefined) {
                this.id = $.trim($('#id').text());
            }
            this.title = $.trim($('#title').val());
            this.contents = $.trim($('#contents').val());
            this.author = $.trim($('#author').val());
            this.category = $.trim($('#category').val());
            this.img = $.trim($('#file-name').val());
        }
    }
}