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
        this.postLikeDto.init(postNo);

        $.ajax({
            type: 'POST'
            , url: '/api/like'
            , contentType: 'application/json'
            , dataType: 'json'
            , data: JSON.stringify(this.postLikeDto)
        }).done(function () {
            alert("좋아요 감사합니다.")
            window.location.reload(true);
        });
    }
};