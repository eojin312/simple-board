/**
 * commentService
 * @type {{commentDto: {postNo: number, init: CommentService.commentDto.init, comment: string}, save: CommentService.save, bindEvent: CommentService.bindEvent}}
 */
CommentService = {

    commentDto: {
        comment: '',
        postNo: 0,
        init: function (postNo, comment) {
            this.postNo = postNo;
            this.comment = comment;
        },
    },
    bindEvent: function () {
        var _this = this;
        $('#comment_button').on('click', this.save);
    },
    save: function () {

        let postNo = $('#post-no').val();
        let comment = $.trim($('#comment').val());
        this.commentDto.init(postNo, comment);

        $.ajax({
            type: 'POST',
            url: '/api/comment',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.commentDto)
        }).done(function (post) {
            alert('정상적으로 댓글이 등록되었습니다');
            window.location.href = '/post/detail/' + post.id;
        }).fail(function (errorResponse) {
            alert('댓글이 작성되지 않았습니다.')
        });
    }
}