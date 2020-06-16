var CommonUtil = {
    localdatetimeToDate: function (localdatetime) {
        if (localdatetime == null || localdatetime == undefined || localdatetime.length < 10) {
            return '';
        }
        return localdatetime.substring(0, 10);
    }
};