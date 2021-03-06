/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.instrument.messaging;

import brave.propagation.Propagation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Taken from Brave.
 *
 * @param <C> carrier type
 * @param <K> key type
 * @author Marcin Grzejszczak
 */
public abstract class PropagationSetterTest<C, K> {

	protected abstract Propagation.KeyFactory<K> keyFactory();

	protected abstract C carrier();

	protected abstract Propagation.Setter<C, K> setter();

	protected abstract Iterable<String> read(C carrier, K key);

	@Test
	public void set() throws Exception {
		K key = keyFactory().create("b3");
		setter().put(carrier(), key, "1");

		assertThat(read(carrier(), key)).containsExactly("1");
	}

	@Test
	public void set128() throws Exception {
		K key = keyFactory().create("b3");
		setter().put(carrier(), key, "1");

		assertThat(read(carrier(), key)).containsExactly("1");
	}

	@Test
	public void setTwoKeys() throws Exception {
		K key1 = keyFactory().create("b3");
		K key2 = keyFactory().create("baggage");
		setter().put(carrier(), key1, "1");
		setter().put(carrier(), key2, "country-code=FO");

		assertThat(read(carrier(), key1)).containsExactly("1");
		assertThat(read(carrier(), key2)).containsExactly("country-code=FO");
	}

	@Test
	public void reset() throws Exception {
		K key = keyFactory().create("b3");
		setter().put(carrier(), key, "0");
		setter().put(carrier(), key, "1");

		assertThat(read(carrier(), key)).containsExactly("1");
	}

}
