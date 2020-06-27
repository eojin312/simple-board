/**
 * 게시물 생성 시간 메소드
 * @type {{localdatetimeToDate: CommonUtil.localdatetimeToDate}}
 */
var CommonUtil = {
    localdatetimeToDate: function (localdatetime) {
        if (localdatetime == null || localdatetime == undefined || localdatetime.length < 10) {
            return '';
        }
        return localdatetime.substring(0, 10);
    }
};