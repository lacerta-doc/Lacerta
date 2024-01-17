# Lacerta
WIP
## モジュール設計

![Untitled Diagram(2)](https://github.com/lacerta-doc/Lacerta/assets/66072112/9daabaca-5cdc-49f8-ac66-196c588a47c9)

## メモ
- アイコン: Google Material Icons https://fonts.google.com/icons (Weight:300, Grade:0, Optical size: 24px)

## モジュール構成
### モジュール一覧
- `component` : コンポーネント
  - `common` : 共通コンポーネント
    - UIの中で共通で使う要素(メニューアイテムとか)
  - `scanner` : スキャナー
    - スキャナーの実装
  - `viewer` : ビューワー
    - ドキュメントビューワーの実装
- `data` : データ
  - UIからデータを取得/保存するためのラッパーモジュール
  - `Document` : ドキュメント関係
    - WIP(JavaDocを参照してください, 余裕があったら追記します)
- `feature` : 機能モジュール(ナビゲーションからみた機能で分割)
  - `common`: 共通機能
    - 設定画面とか、どこからでも呼ばれうる画面
  - `debug`: デバッグメニュー
  - `home`: ホーム画面
  - `library`: ライブラリ画面
  - `scan`: スキャン画面
  - `search`: 検索画面
- `model` : モデル
  - データモデルをまとめたモジュール (WIP)
  - `document` : ドキュメント
  - `meta` : メタデータ
- `processor` : プロセッサ
  - いくつかの処理をまとめたモジュール
    - `DocumentProcessor` : ドキュメント処理（ドキュメントにページを追加したり更新したり） 
- `shared` : 
  - 共有リソース
- `source` : ソース
  - (バックエンドが使うのでとりあえず後回し、フロントから直接操作することは絶対にありません)
- `utils` : ユーティリティ
  - ちょっとしたユーティリティをまとめたモジュール
  - `LacertaLogger` : ロガー
  - `XmlMetaParser` : XMLメタデータパーサー(フロントから直接操作することは絶対にありません)


## コーディング規則/推奨(WIP)
### 規則
- `// TODO`コメントには任意のsuffixを付ける
  - 全員が同じ`// TODO:`を使っていると検索がむずかしくなるため
  - (例: `// TODO-rca:`)
### 推奨
- マジックナンバーは控える(必要な場合もあるので)

