/*
 * Copyright © 2018 Apple Inc. and the ServiceTalk project authors
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
package io.servicetalk.http.api;

import io.servicetalk.concurrent.api.Completable;
import io.servicetalk.concurrent.api.Single;
import io.servicetalk.transport.api.ExecutionContext;

import static java.util.Objects.requireNonNull;

final class HttpRequesterToStreamingHttpRequester extends StreamingHttpRequester {
    private final HttpRequester requester;

    HttpRequesterToStreamingHttpRequester(final HttpRequester requester) {
        super(new HttpRequestResponseFactoryToStreamingHttpRequestResponseFactory(requester.reqRespFactory));
        this.requester = requireNonNull(requester);
    }

    @Override
    public Single<StreamingHttpResponse> request(final StreamingHttpRequest request) {
        return request.toRequest().flatMap(requester::request).map(HttpResponse::toStreamingResponse);
    }

    @Override
    public ExecutionContext getExecutionContext() {
        return requester.getExecutionContext();
    }

    @Override
    public Completable onClose() {
        return requester.onClose();
    }

    @Override
    public Completable closeAsync() {
        return requester.closeAsync();
    }

    @Override
    public Completable closeAsyncGracefully() {
        return requester.closeAsyncGracefully();
    }

    @Override
    HttpRequester asRequesterInternal() {
        return requester;
    }
}