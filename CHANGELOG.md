# アプリケーション変更履歴

<details>
<summary><b>fix: エラー修正</b> (クリックで展開)</summary>

### 2026-02-03: 用具一覧画面への遷移時に発生するエラー修正

- Thymeleafで 'th:each="eq : ${equipmentList}"' のように予約語である 'eq' を変数名としていた
    - 変数名を 'equipment (意味のある単数系名詞)' へ変更して解決

</details>

<details>
<summary><b>feat: 機能追加</b> (クリックで展開)</summary>

</details>

<details>
<summary><b>refactor: リファクタリング</b> (クリックで展開)</summary>

### 2026-02-05: Thymeleaf Layout Dialectを使ってコードを共通化

- 子ページから個別に呼び出す形ではなくなったのでコードの見通しが良くなった

### 2026-02-04: sやcなどで変数定義していた箇所を可読性のため修正

- sやcなどで変数定義していた箇所を意味のある単数形名詞 (category など)へ修正

</details>