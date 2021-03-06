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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import brave.sampler.SamplerFunction;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Annotate a producer {@link SamplerFunction} that should be injected to
 * {@link brave.messaging.MessagingTracing.Builder#producerSampler(SamplerFunction)}.
 *
 * @since 2.2.0
 * @see Qualifier
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier(ProducerSampler.NAME)
public @interface ProducerSampler {

	/**
	 * Default name for messaging producer sampler.
	 */
	String NAME = "sleuthProducerSampler";

}
