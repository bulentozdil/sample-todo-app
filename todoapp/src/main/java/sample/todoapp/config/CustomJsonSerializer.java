package sample.todoapp.config;

import static com.couchbase.client.core.logging.RedactableArgument.redactUser;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.couchbase.client.core.error.DecodingFailureException;
import com.couchbase.client.core.error.EncodingFailureException;
import com.couchbase.client.java.codec.JsonSerializer;
import com.couchbase.client.java.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJsonSerializer implements JsonSerializer {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public byte[] serialize(
			Object input) {
		try {
			return mapper.writeValueAsBytes(input);
		} catch (Throwable t) {
			throw new EncodingFailureException("Serializing of content + " + redactUser(input) + " to JSON failed.", t);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public <T> T deserialize(
			Class<T> target,
			byte[] input) {
		var obj = JsonObject.create().fromJson(input);
		try {
			var value = obj.get(target.getSimpleName().toLowerCase());
			if (value != null) {
				return (T) mapper.readValue(value.toString(), target);
			}
			return (T) mapper.readValue(obj.toString(), target);
		} catch (JsonProcessingException e) {
			throw new DecodingFailureException("Deserialization of content into target " + target + " failed; encoded = " + redactUser(new String(input, UTF_8)), e);
		}
	}
}
