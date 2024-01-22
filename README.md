# Lacerta
学習に便利な機能とGit-like VCSを持ったドキュメントスキャナ (Work In Progress)

## モジュール設計
![Untitled Diagram(2)(1) drawio(1)](https://github.com/lacerta-doc/Lacerta/assets/66072112/f9b9c40f-bed0-4ade-95c1-50e28df68f35)


## メモ
- アイコン: Google Material Icons https://fonts.google.com/icons (Weight:300, Grade:0, Optical size: 24px)

## モジュール構成
### モジュール一覧
- `component` : コンポーネント
  - `common` : 共通コンポーネント
  - `scanner` : スキャナー 
  - `viewer` : ビューワー
- `data` : UIからデータを取得/保存するためのラッパーモジュール
- `feature` : 機能モジュール(ナビゲーションからみた機能で分割)
  - `common`: 共通機能
  - `debug`: デバッグメニュー
  - `home`: ホーム画面
  - `library`: ライブラリ画面
  - `scan`: スキャン画面 (廃止予定)
  - `search`: 検索画面
- `model` : データモデルをまとめたモジュール
- `processor` : プロセッサ（例: `DocumentProcessor` : ドキュメント処理（ドキュメントにページを追加したり更新したり）)
- `shared`: 共有リソース
  - `ui`: UI要素(Theme, Drawable, Colorなど)
- `source` : ソース(DB/FileManagerなど)
- `utils` : ユーティリティ(ちょっとしたユーティリティをまとめたモジュール)

## コーディング規則/推奨(WIP)
- `// TODO`コメントには任意のsuffixを付ける
  - 全員が同じ`// TODO:`を使っていると検索がむずかしくなるため
  - (例: `// TODO-rca:`)
 
## Thanks for
- [SDA-SE/document-scanner-android](https://github.com/SDA-SE/document-scanner-android)
- 
