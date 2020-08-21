var UploadService = {
    upload: function () {
        let form = $('#upload-form')[0];
        let data = new FormData(form);
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            url: '/api/multipleupload',
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