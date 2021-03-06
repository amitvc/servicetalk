/*
 * Copyright © 2019 Apple Inc. and the ServiceTalk project authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicetalk.grpc.api;

import io.servicetalk.encoding.api.ContentCodec;

import java.time.Duration;
import javax.annotation.Nullable;

import static io.servicetalk.grpc.internal.DeadlineUtils.EIGHT_NINES;

/**
 * Metadata for a <a href="https://www.grpc.io">gRPC</a> client call.
 */
public interface GrpcClientMetadata extends GrpcMetadata {

    /**
     * Maximum timeout which can be specified for a <a href="https://www.grpc.io">gRPC</a>
     * <a href="https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-HTTP2.md#requests">request</a>. Note that this
     * maximum is effectively infinite as the duration is more than 11,000 years.
     *
     * @deprecated Do not use. This constant will be removed in future releases. If necessary, define an alternative
     * constant in your application or use {@code null} for infinite {@link #timeout() timeout}.
     */
    @Deprecated
    Duration GRPC_MAX_TIMEOUT = Duration.ofHours(EIGHT_NINES);

    /**
     * {@link GrpcExecutionStrategy} to use for the associated
     * <a href="https://www.grpc.io">gRPC</a> method.
     *
     * @return {@link GrpcExecutionStrategy} to use for the associated
     * <a href="https://www.grpc.io">gRPC</a> method.
     */
    @Nullable
    GrpcExecutionStrategy strategy();

    /**
     * {@link ContentCodec} to use for the associated
     * <a href="https://www.grpc.io">gRPC</a> method.
     *
     * @return {@link ContentCodec} to use for the associated
     * <a href="https://www.grpc.io">gRPC</a> method.
     */
    ContentCodec requestEncoding();

    /**
     * Returns timeout duration after which the response is no longer wanted.
     *
     * @return {@link Duration} of associated timeout or null for no timeout
     * @see <a href="https://grpc.io/blog/deadlines/">gRPC Deadlines</a>
     */
    @Nullable
    Duration timeout();
}
