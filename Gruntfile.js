module.exports = function(grunt) {
  grunt.initConfig({
    root_dir : './src/main/webapp',
    pkg : grunt.file.readJSON("package.json"),

    compass : {
      dev : {
        options : {
          httpPath:"/",
          cssDir : "<%= root_dir %>/css",
          sassDir : "<%= root_dir %>/scss",
          imagesDir : "<%= root_dir %>/img",
          javascriptsDir : "<%= root_dir %>/js",
          httpGeneratedImagesPath : "/img",
          httpImagesPath : "/img",
          outputStyle : "expanded",
          environment : "development",
        }
      },
      prod : {
        options : {
          http_path:"/",
          cssDir : "<%= root_dir %>/css",
          sassDir : "<%= root_dir %>/scss",
          imagesDir : "<%= root_dir %>/img",
          javascriptsDir : "<%= root_dir %>/js",
          httpGeneratedImagesPath : "/img",
          httpImagesPath : "/img",
          outputStyle : "compressed",
          environment : 'production'
        }
      }
    },

    cssmin : {//ミニファイ
      minify : {
        src : [
               '<%= root_dir %>/css/component/*.css',
               '<%= root_dir %>/css/project/*.css',
               '<%= root_dir %>/css/utility/*.css'
               ],
        dest : '<%= root_dir %>/css/main.min.css'
      }
    },
    
    replace : {//環境依存関係の設定値を解決
    	common_config : {
    		src : [ '<%= root_dir %>/js/commons/config.setting' ],
    		dest : '<%= root_dir %>/js/commons/config.js',
    	},	
		local : {
			src: '<%= replace.common_config.src %>',
			dest: '<%= replace.common_config.dest %>',
			replacements : [
                {
                	from : '#{environment}',
                	to : 'LOCAL'
                },{
                	from : '#{baseUrl}',
                	to : 'http://localhost:8080'
                }
			]	
		},
		dev : {
			src: '<%= replace.common_config.src %>',
			dest: '<%= replace.common_config.dest %>',
			replacements : [
                {
                	from : '#{environment}',
                	to : 'DEVLOPMENT'
                },{
                	from : '#{baseUrl}',
                	to : 'http://dev.p.server'
                }
			]	
		},
		prd : {
			src: '<%= replace.common_config.src %>',
			dest: '<%= replace.common_config.dest %>',
			replacements : [
                {
                	from : '#{environment}',
                	to : 'PRODUCTION'
                },{
                	from : '#{baseUrl}',
                	to : 'http://p.server'
                }
			]	
    	}
    },

    concat : {//１ファイルにまとめる。
      js_lib : {//ライブラリ関連
        src : [
//            '<%= root_dir %>/js/lib/requirejs/require.js',
            '<%= root_dir %>/js/lib/jquery/jquery.min.js',
            '<%= root_dir %>/js/lib/jquery-ui/jquery-ui.min.js',
            '<%= root_dir %>/js/lib/underscore/underscore-min.js',
            '<%= root_dir %>/js/lib/backbone/backbone-min.js',
            '<%= root_dir %>/js/lib/handlebars/handlebars.min.js'
            ],
        dest : '<%= root_dir %>/js/all_lib.js'
      },
      js_app : {
    	  src : [ '<%= root_dir %>/js/commons/**/*.js',
    	          '<%= root_dir %>/js/commons/*.js',
    	          '<%= root_dir %>/js/models/**/*.js',
    	          '<%= root_dir %>/js/views/**/*.js',
    	          '<%= root_dir %>/js/views/*.js'
    	         ],
    	  dest : '<%= root_dir %>/js/app.js'       
      }
    },

    uglify : {//jsファイルを圧縮するタスク
      js_lib : {
        src : '<%= concat.js_lib.dest %>',
        dest : '<%= root_dir %>/js/all_lib.min.js'
      },
      js_app : {
    	  src : '<%= concat.js_app.dest %>',
    	  dest : '<%= root_dir %>/js/app.min.js'
      }
    },
    
    clean : {//css,js削除
    	css: [ '<%= root_dir %>/css/component/*.css',
    	       '<%= root_dir %>/css/project/*.css',
    	       '<%= root_dir %>/css/utility/*.css',
    	       '<%= cssmin.minify.dest %>' ],
    	js: [ '<%= concat.js_lib.dest %>',
    	      '<%= uglify.js_lib.dest %>']
    },

    watch : {
      js : {
        files : [ '<%= root_dir %>/js/**/*.js' ],
        tasks : [ 'concat', 'uglify' ]
      // 実行させるタスク
      },
      scss : {
        files : [ '<%= root_dir %>/scss/**/*.scss', ],
        tasks : [ 'compass:dev' ]
      },
      css : {
        files : [
          '<%= root_dir %>/css/component/*.css',
          '<%= root_dir %>/css/project/*.css',
          '<%= root_dir %>/css/utility/*.css'
        ],
        tasks : [ 'cssmin' ]
      // 実行させるタスク
      }
    }
  });
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-compass');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-handlebars');
  grunt.loadNpmTasks('grunt-text-replace');
  
  grunt.registerTask('local', [ 'clean', 'replace:local', 'compass:dev', 'concat', 'uglify', 'cssmin' ]);
  grunt.registerTask('local_watch',
      [ 'clean', 'replace:local', 'compass:dev', 'concat', 'uglify', 'cssmin', 'watch' ]);

  grunt.registerTask('dev', [ 'clean', 'replace:dev', 'compass:dev', 'concat', 'uglify', 'cssmin' ]);
  grunt.registerTask('dev_watch',
      [ 'clean', 'replace:dev','compass:dev', 'concat', 'uglify', 'cssmin', 'watch' ]);
  
  grunt.registerTask('prd', [ 'clean', 'replace:prd', 'compass:prd', 'concat', 'uglify', 'cssmin' ]);
}
