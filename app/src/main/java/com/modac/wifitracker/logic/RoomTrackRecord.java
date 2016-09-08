package com.modac.wifitracker.logic;

import android.util.Log;

import java.security.InvalidParameterException;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Keller2 on 23.06.2016.
 */
public class RoomTrackRecord extends TrackRecord {

    private static String TAG = "RoomTrackRecord";

    protected String room;



    public RoomTrackRecord(String room, Set<WifiApRecord> wifiApRecords) {
        super(wifiApRecords);
        this.room = room;
    }

    public RoomTrackRecord(String room) {
        this(room, new HashSet<WifiApRecord>());
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String serialize() {
        return "'" + getRoom() + "'" + Strings.RTR_SERIALIZE_FIELD_SPACER + super.serialize();
    }

    public static RoomTrackRecord deserialize(String string) throws InvalidParameterException {
        String[] parts = string.split(Strings.RTR_SERIALIZE_FIELD_SPACER);
        if (parts.length != 2 || !hasQuotes(parts[0])) {
            throw new InvalidParameterException("Invalid string of serialized RoomTrackRecord");
        }

        String room = trimQuotes(parts[0]);
        TrackRecord tr = TrackRecord.deserialize(parts[1]);
        return new RoomTrackRecord(room, tr.getWifiApRecords());
    }

}
