/**
 * commentService
 * @type {{commentDto: {postNo: number, init: CommentService.commentDto.init, comment: string}, save: CommentService.save, bindEvent: CommentService.bindEvent}}
 */
var CommentService = {

    commentDto: {
        contents: '',
        postNo: 0,
        init: function (postNo, contents) {
            this.postNo = postNo;
            this.contents = contents;
        },
    },
    bindEvent: function () {
        var _this = this;
        $('#comment_button').on('click', this.save);
    },
    save: function () {

        let postNo = $('#post-no').val();
        let contents = $.trim($('#contents').val());
        this.commentDto.init(postNo, contents);

        $.ajax({
            type: 'POST',
            url: '/api/comment',
            contentType: 'application/json',
            datatype: 'json',
            data: JSON.stringify(this.commentDto)
        }).done(function () {
            alert('정상적으로 댓글이 등록되었습니다');
            window.location.reload(true);
        }).fail(function (errorResponse) {
            alert('댓글이 작성되지 않았습니다.')
        });
    }
};