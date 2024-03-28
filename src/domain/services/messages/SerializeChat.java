package src.domain.services.messages;

import src.domain.entities.messages.Chat;

public class SerializeChat {
    /**
     * Serializes a chat to a JSON string.
     * @param chat The chat to be serialized.
     * @return The JSON string representation of the chat.
     */
    public static String toJsonString(Chat chat) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        appendField(jsonBuilder, "id", chat.getId().toString(), true);
        appendField(jsonBuilder, "userA", chat.getUserA().getUsername(), false);
        appendField(jsonBuilder, "userB", chat.getUserB().getUsername(), false);
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    /**
     * Appends a key-value pair to a JSON string.
     * @param builder The StringBuilder object to append to.
     * @param key The key of the key-value pair.
     * @param value The value of the key-value pair.
     * @param first A boolean indicating whether the key-value pair is the first in the JSON string.
     */
    private static void appendField(StringBuilder builder, String key, String value, boolean first) {
        if (!first) {
            builder.append(",");
        }
        builder.append("\"").append(key).append("\":\"").append(value).append("\"");
    }
}
