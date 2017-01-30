define(function(){

	return {
		ENV : 'LOCAL',
		BASE_URL : 'http://localhost:8080',
		API_PATH : '/api/v1',
		/**
		 * APIのURI
		 */
		API : {
			USERS : '/users',
			ROOMS : '/rooms',
			MESSAGES : '/rooms/{roomId}/messages',
		}
		/**
		 * ページURI
		 */
	};
});
define(['config'], function (config) {

  return {
    log: function (value) {
      if (config.ENV === 'LOCAL') {
        if (console) {
          console.log(value)
        }
      }
    },

    reqUrl: function () {

    }
  };
});
define(["utils"], function (utils) {
  /**
   *
   */
  var RoomModel = Backbone.Model.extend({});
});
