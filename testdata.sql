-- テストデータ
-- 競技テーブル
insert into Competition values(1, "トライジドリング", 150, "3枚撮る");
insert into Competition values(2, "シンクロジドリング", 100, "お題に沿って撮る");
insert into Competition values(3, "スプラトゥーチ", 50, "光って面積を取れ");

-- タイトルテーブル
-- 1XX で トライ, 2XX で シンクロ用タイトル
insert into title values(101, 1, "アンパンマン");
insert into title values(102, 1, "ヤギ");
insert into title values(103, 1, "槍投げ");
insert into title values(104, 1, "クマ");
insert into title values(105, 1, "チンピラ");
insert into title values(106, 1, "美少女");
insert into title values(107, 1, "イケメン");
insert into title values(108, 1, "仏");
insert into title values(109, 1, "そいつは驚いた");
insert into title values(110, 1, "休憩");
insert into title values(111, 1, "HEW金賞");
insert into title values(112, 1, "大凶");
insert into title values(113, 1, "失恋");
insert into title values(114, 1, "信じられない");
insert into title values(115, 1, "マジギレ");
insert into title values(116, 1, "わかる");
insert into title values(117, 1, "ねこ");
insert into title values(118, 1, "サボテン");
insert into title values(201, 2, "必殺技");
insert into title values(202, 2, "芸人");
insert into title values(203, 2, "職業");


-- 管理者テーブル
insert into admin (admin_id, name, password, delete_flag) values ("suto", "すとう", "40721", 0);

-- 利用者テーブル
insert into member values(1, "secon", "secon", "mogemoge", "secon@example.com", 19930416, "m", 0);
insert into member values(2, "mikan", "mikan", "45006", "mikan@gmail", 1992-09-25, "w", 0);
insert into member values(3, "suto", "suto", "40721", "suto@gmail", 1992-01-29, "m", 0);

-- 画像詳細テーブル
insert into contribution_details(contribution_id,title_id,img_pass,img_title) value(1, 1, "test0.png", "ほんだつばさ");
insert into admin (admin_id, name, password, delete_flag) values ("mikan", "みかん", "45006", 0);
insert into admin (admin_id, name, password, delete_flag) values ("secon", "secon", "0416", 0);
