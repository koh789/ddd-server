personal_server
===============
spring4.0


##[Gruntローカル導入手順]
既にインストール済みのものはスキップ

#① gruntインストール
npm update
sudo npm install -g grunt-cli

#② gruntで使用するnodeモジュールインストール

sudo npm install grunt grunt-contrib-jshint grunt-contrib-sass grunt-contrib-uglify grunt-contrib-watch grunt-contrib-concat grunt-contrib-cssmin grunt-contrib-compass grunt-contrib-clean grunt-contrib-handlebars grunt-text-replace --save-dev
  
#③ ローカル実行。(scssのコンパイルのみ)
grunt local

#③ ローカルscssコンパイル + watch実行。
grunt local_watch

#[補足]devでコンパイル
grunt dev_compile

#[補足]stgでコンパイル＋js,cssの圧縮
grunt stg_compile
  
# （おまけ）sass,compass導入
sudo gem update --system
sudo gem install sass
sudo gem install compass

##[bower導入手順]
#①bowerで管理しているライブラリのinstall
npm install bower -g

#②バージョン確認
bower -v

#[補足]bower使用
・定義ライブラリ確認
  bower list
・定義ライブラリローカルインストール
bower install
mv bower_components/* ./src/main/webapp/lib/