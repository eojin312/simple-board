/**
 * commentService
 * @type {{commentDto: {postNo: number, init: CommentService.commentDto.init, comment: string}, save: CommentService.save, bindEvent: CommentService.bindEvent}}
 */
var CommentService = {

    commentDto: {
        comments: '',
        postNo: 0,
        init: function (postNo, comments) {
            this.postNo = postNo;
            this.comments = comments;
        },
    },
    bindEvent: function () {
        var _this = this;
        $('#comment_button').on('click', this.save);
    },
    save: function () {

        let postNo = $('#post-no').val();
        let comments = $.trim($('#comments').val());
        this.commentDto.init(postNo, comments);

        $.ajax({
            type: 'POST',
            url: '/api/comment',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.commentDto)
        }).done(function (savedPostId) {
            alert('정상적으로 댓글이 등록되었습니다');
            window.location.reload(true);
        }).fail(function (errorResponse) {
            alert('댓글이 작성되지 않았습니다.')
        });
    }
};