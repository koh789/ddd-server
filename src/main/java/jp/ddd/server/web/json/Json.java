package jp.ddd.server.web.json;

import java.io.Serializable;

import lombok.Data;

/**
 * APIのレスポンスを表現するマーカーインターフェースです。
 * Json変換のためlombokの@valueは利用できません。
 * 当オブジェクトはシャローコピー{@link Object#clone}の
 * 使用を推奨していません。
 * 個別にファクトリメソッドを作成してください。
 * @author noguchi_kohei
 *
 */
public interface Json extends Serializable {
}
