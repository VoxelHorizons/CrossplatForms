#!/usr/bin/env python3
"""Check local documentation links, using Python's standard library only."""
from pathlib import Path
from urllib.parse import unquote, urlsplit
import re
import sys

ROOT = Path(__file__).resolve().parents[1]
FILES = [ROOT / "README.md", ROOT / "CONTRIBUTING.md"] + sorted((ROOT / "docs").rglob("*.md"))
MARKDOWN_LINK = re.compile(r"(?<!!)\[[^\]]+\]\(([^)]+)\)")
HTML_IMAGE = re.compile(r"""<img[^>]+src=["']([^"']+)["']""")
errors = []
count = 0

if not (ROOT / "docs/index.md").is_file():
    errors.append("missing docs/index.md")

for source in FILES:
    if not source.exists():
        errors.append(f"missing {source.relative_to(ROOT)}")
        continue
    body = source.read_text(encoding="utf-8")
    for stale in ("github.com/kejonaMC/CrossplatForms/wiki", "ci.kejonamc.dev/"):
        if stale.lower() in body.lower():
            errors.append(f"{source.relative_to(ROOT)} links to obsolete {stale}")
    fenced = False
    for number, line in enumerate(body.splitlines(), 1):
        if line.strip().startswith(("~~~", "```")):
            fenced = not fenced
            continue
        if fenced:
            continue
        for match in list(MARKDOWN_LINK.finditer(line)) + list(HTML_IMAGE.finditer(line)):
            url = match.group(1).split(" ", 1)[0].strip()
            if not url or url.startswith(("#", "mailto:", "data:")):
                continue
            parts = urlsplit(url)
            if parts.scheme or parts.netloc or url.startswith("//"):
                continue
            target = (source.parent / unquote(parts.path)).resolve()
            count += 1
            if not target.is_relative_to(ROOT) or not target.exists():
                errors.append(f"{source.relative_to(ROOT)}:{number}: invalid local link: {url}")

if errors:
    print("\n".join(errors), file=sys.stderr)
    sys.exit(1)
print(f"Documentation validated: {len(FILES)} pages, {count} local links.")
