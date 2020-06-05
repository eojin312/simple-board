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
    }
}