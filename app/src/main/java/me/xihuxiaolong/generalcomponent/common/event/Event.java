package me.xihuxiaolong.generalcomponent.common.event;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/12.
 */
public class Event {

    public static class DeleteShortNote {

        public long shortNoteId;

        public DeleteShortNote(long shortNoteId) {
            this.shortNoteId = shortNoteId;
        }
    }

    public static class AddShortNote {

        public DBShortNote shortNote;

        public AddShortNote(DBShortNote shortNote) {
            this.shortNote = shortNote;
        }
    }

    public static class UpdateShortNote {

        public DBShortNote shortNote;

        public UpdateShortNote(DBShortNote shortNote) {
            this.shortNote = shortNote;
        }
    }
}
