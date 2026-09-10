#!/usr/bin/env bash
# Local test harness: generate + compile this template from the working tree.
set -euo pipefail
cd "$(dirname "$0")"
OUT="$PWD/test-template"

command -v g8 >/dev/null || { echo "g8 not found. Install: cs install giter8"; exit 1; }

echo "Which path do you want to test?"
echo "  1) fullstack      (backend=yes)"
echo "  2) frontend only  (backend=no)"
echo "  3) both"
read -rp "> " choice || choice=""   # bare `read` at EOF would exit silently under set -e
case "$choice" in
  1) targets=(fullstack) ;;
  2) targets=(frontend-only) ;;
  3) targets=(fullstack frontend-only) ;;
  *) echo "invalid choice: '$choice'"; exit 1 ;;
esac

# g8 clones the template, so file://$PWD would only see committed state.
# Snapshot the working tree into a throwaway repo so uncommitted edits get tested.
TPL=$(mktemp -d)
trap 'rm -rf "$TPL"' EXIT
cp -R src "$TPL/"
git -C "$TPL" init -q -b main
git -C "$TPL" add -Af   # -f: src/main/g8/.gitignore would otherwise apply to the payload
git -C "$TPL" -c user.email=t@t -c user.name=t -c commit.gpgsign=false commit -qm snapshot

rm -rf "$OUT"; mkdir -p "$OUT"

for t in "${targets[@]}"; do
  [ "$t" = fullstack ] && backend=yes || backend=no
  echo "==> generating $t (backend=$backend)"
  g8 "file://$TPL" --backend="$backend" -o "$OUT/$t" </dev/null
  # g8 can report success without producing anything; make that loud, not silent.
  [ -f "$OUT/$t/build.sbt" ] || { echo "ERROR: $t generated no build.sbt"; exit 1; }
done

failed=""
for t in "${targets[@]}"; do
  # ponytail: fullstack root project has no .aggregate(), so `compile` alone
  # compiles nothing and falsely passes. Name the two leaf modules instead;
  # common.js/common.jvm come along via dependsOn.
  [ "$t" = fullstack ] && cmd="backend/compile frontend/compile" || cmd="compile"
  echo "==> compiling $t"
  # </dev/null: sbt reads stdin and would otherwise eat the terminal
  (cd "$OUT/$t" && sbt -batch $cmd </dev/null) || failed="$failed $t"
done

[ -n "$failed" ] && { echo "compile FAILED:$failed"; exit 1; }

echo
echo "Generated in $OUT:"
ls -1 "$OUT"
