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