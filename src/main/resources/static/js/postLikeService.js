var postLikeService = {
    postLikeDto: {
        postNo: 0,
        init: function (postNo) {
            this.postNo = postNo;
        },
    },
    bindEvent: function () {
        var _this = this
        $('#vote-like').on('click', this.save());
    },
    save: function () {
        let postNo = $('#post-no').val();

        $.ajax({
            type: 'POST'
            , url: '/api/like/' + postNo
            , contentType: 'application/json'
            , dataType: 'json'
        }).done(function (likeCount) {
            if (likeCount < 0) {
                alert('이미 좋아요를 누르셨습니다.');
                return;
            }
            alert("좋아요 감사합니다.");
            $('#likeCount').text(likeCount);
        });
    }
};