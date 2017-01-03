define(function () {

  return {
    ENV: 'LOCAL',
    BASE_URL: 'http://localhost:8080',
    API_PATH: '/api/v1',
    /**
     * APIのURI
     */
    API: {
      USERS: '/users',
      ROOMS: '/rooms',
      MESSAGES: '/rooms/{roomId}/messages',
    }
    /**
     * ページURI
     */
  };
});