/**
 * 게시물 생성 시간 메소드
 * @type {{localdatetimeToDate: CommonUtil.localdatetimeToDate}}
 */
var CommonUtil = {
    localdatetimeToDate: function (localdatetime) {
        if (localdatetime == null || localdatetime == undefined || localdatetime.length < 15) {
            return '';
        }
        return localdatetime.substring(11, 16);
    }
};