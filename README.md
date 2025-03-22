Resolved in https://github.com/clj-kondo/clj-kondo/pull/2505.

# clj-kondo analysis - var-usage nils repro
This repo shows clj-kondo occasionally producing `:name-col nil` var-usages.
From my reading of the [docs][], this shouldn't happen since a using a var
always requires you to use its name and the analysis should include the
corresponding location info. Further, `:row`, `:col` and `:name-row` are
also often nil, but I didn't include this in the dataset.

Run with:
```
clj -M -m analyze ~/repos/fast-edn/test/fast_edn/test.clj
```

See:
- `filtered.edn`: only usages where `:name-col nil`
- `labeled.edn`: all usages along with an added `:nil-name-col?` field

This example arbitrarily looks at [fast-edn][] `3a4cdbd`, but I found many
such examples in the [Dewey][] dataset. Here's a list of some of the top
results where `name-col` is null (total 1,270,235 records):

```
name                |count_|
--------------------+------+
.                   |709485|
fn*                 |222290|
if                  | 41667|
->                  | 38968|
let                 | 38638|
assoc               | 14944|
is                  | 14669|
conj                | 11358|
+                   |  9212|
first               |  7143|
inc                 |  6422|
nil?                |  5971|
vector              |  5836|
dissoc              |  4700|
identity            |  4429|
second              |  4395|
assoc-in            |  3565|
merge               |  3300|
str                 |  2845|
update-in           |  2714|
update              |  2701|
->>                 |  2699|
do                  |  1970|
into                |  1817|
name                |  1758|
keyword             |  1587|
some?               |  1258|
*                   |  1236|
count               |  1215|
concat              |  1149|
disj                |  1095|
not                 |  1094|
dec                 |  1078|
vec                 |  1039|
-                   |   972|
max                 |   967|
true?               |   956|
empty?              |   878|
read-string         |   835|
symbol?             |   822|
map?                |   796|
```

[docs]: https://github.com/clj-kondo/clj-kondo/tree/master/analysis
[fast-edn]: https://github.com/tonsky/fast-edn
[Dewey]: https://github.com/phronmophobic/dewey
