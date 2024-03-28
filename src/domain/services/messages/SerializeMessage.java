package src.domain.services.messages;

import src.domain.entities.messages.Message;

public class SerializeMessage {
    /**
     * Serializes a message to a JSON string.
     * @param message The message to be serialized.
     * @return The JSON string representation of the message.
     */
    public static String toJsonString(Message message) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        appendField(jsonBuilder, "id", message.getId().toString(), true);
        appendField(jsonBuilder, "chatId", message.getChatId().toString(), false);
        appendField(jsonBuilder, "owner", message.getOwner().getUsername(), false);
        appendField(jsonBuilder, "content", message.getContent(), false);
        appendField(jsonBuilder, "timestamp", message.getTimestamp().toString(), false);
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
            builder.append(", ");
        }
        builder.append("\"").append(key).append("\":\"").append(value).append("\"");
    }
}
