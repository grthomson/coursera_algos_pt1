from datetime import datetime
from typing import Tuple

def norm(s: Optional[str]) -> str:
    """Basic string normalisation."""
    return "" if s is None else " ".join(s.strip().lower().split())

def dedupe_keep_latest(records: List[dict]) -> List[dict]:
    """
    Keeps the latest record per canonical key.
    Uses a dict as a lookup table from key -> best record seen so far.
    """
    best_by_key: dict[Tuple[str, str, str], dict] = {}

    for r in records:
        key = (norm(r.get("first_name")), norm(r.get("last_name")), r.get("dob", ""))

        # Parse timestamps; assume ISO 8601 strings for simplicity
        ts = datetime.fromisoformat(r["updated_at"])

        if key not in best_by_key:
            best_by_key[key] = r
        else:
            existing_ts = datetime.fromisoformat(best_by_key[key]["updated_at"])
            if ts > existing_ts:
                best_by_key[key] = r  # replace with newer record

    return list(best_by_key.values())
