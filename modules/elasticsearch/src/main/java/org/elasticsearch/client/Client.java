/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.client;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.count.CountRequest;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequest;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.mlt.MoreLikeThisRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.terms.TermsRequest;
import org.elasticsearch.action.terms.TermsResponse;

/**
 * A client provides a one stop interface for performing actions/operations against the cluster.
 *
 * <p>All operations performed are asynchronous by nature. Each action/operation has two flavors, the first
 * simply returns an {@link org.elasticsearch.action.ActionFuture}, while the second accepts an
 * {@link org.elasticsearch.action.ActionListener}.
 *
 * <p>A client can either be retrieved from a {@link org.elasticsearch.server.Server} started, or connected remotely
 * to one or more nodes using {@link org.elasticsearch.client.transport.TransportClient}.
 *
 * @author kimchy (shay.banon)
 * @see org.elasticsearch.server.Server#client()
 * @see org.elasticsearch.client.transport.TransportClient
 */
public interface Client {

    /**
     * Closes the client.
     */
    void close();

    /**
     * The admin client that can be used to perform administrative operations.
     */
    AdminClient admin();

    /**
     * Index a JSON source associated with a given index and type.
     *
     * <p>The id is optional, if it is not provided, one will be generated automatically.
     *
     * @param request The index request
     * @return The result future
     * @see Requests#indexRequest(String)
     */
    ActionFuture<IndexResponse> index(IndexRequest request);

    /**
     * Index a JSON source associated with a given index and type.
     *
     * <p>The id is optional, if it is not provided, one will be generated automatically.
     *
     * @param request  The index request
     * @param listener A listener to be notified with a result
     * @see Requests#indexRequest(String)
     */
    void index(IndexRequest request, ActionListener<IndexResponse> listener);

    /**
     * Deletes a document from the index based on the index, type and id.
     *
     * @param request The delete request
     * @return The result future
     * @see Requests#deleteRequest(String)
     */
    ActionFuture<DeleteResponse> delete(DeleteRequest request);

    /**
     * Deletes a document from the index based on the index, type and id.
     *
     * @param request  The delete request
     * @param listener A listener to be notified with a result
     * @see Requests#deleteRequest(String)
     */
    void delete(DeleteRequest request, ActionListener<DeleteResponse> listener);

    /**
     * Deletes all documents from one or more indices based on a query.
     *
     * @param request The delete by query request
     * @return The result future
     * @see Requests#deleteByQueryRequest(String...)
     */
    ActionFuture<DeleteByQueryResponse> deleteByQuery(DeleteByQueryRequest request);

    /**
     * Deletes all documents from one or more indices based on a query.
     *
     * @param request  The delete by query request
     * @param listener A listener to be notified with a result
     * @see Requests#deleteByQueryRequest(String...)
     */
    void deleteByQuery(DeleteByQueryRequest request, ActionListener<DeleteByQueryResponse> listener);

    /**
     * Gets the JSON source that was indexed from an index with a type and id.
     *
     * @param request The get request
     * @return The result future
     * @see Requests#getRequest(String)
     */
    ActionFuture<GetResponse> get(GetRequest request);

    /**
     * Gets the JSON source that was indexed from an index with a type and id.
     *
     * @param request  The get request
     * @param listener A listener to be notified with a result
     * @see Requests#getRequest(String)
     */
    void get(GetRequest request, ActionListener<GetResponse> listener);

    /**
     * A count of all the documents matching a specific query.
     *
     * @param request The count request
     * @return The result future
     * @see Requests#countRequest(String...)
     */
    ActionFuture<CountResponse> count(CountRequest request);

    /**
     * A count of all the documents matching a specific query.
     *
     * @param request  The count request
     * @param listener A listener to be notified of the result
     * @see Requests#countRequest(String...)
     */
    void count(CountRequest request, ActionListener<CountResponse> listener);

    /**
     * Search across one or more indices and one or more types with a query.
     *
     * @param request The search request
     * @return The result future
     * @see Requests#searchRequest(String...)
     */
    ActionFuture<SearchResponse> search(SearchRequest request);

    /**
     * Search across one or more indices and one or more types with a query.
     *
     * @param request  The search request
     * @param listener A listener to be notified of the result
     * @see Requests#searchRequest(String...)
     */
    void search(SearchRequest request, ActionListener<SearchResponse> listener);

    /**
     * A search scroll request to continue searching a previous scrollable search request.
     *
     * @param request The search scroll request
     * @return The result future
     * @see Requests#searchScrollRequest(String)
     */
    ActionFuture<SearchResponse> searchScroll(SearchScrollRequest request);

    /**
     * A search scroll request to continue searching a previous scrollable search request.
     *
     * @param request  The search scroll request
     * @param listener A listener to be notified of the result
     * @see Requests#searchScrollRequest(String)
     */
    void searchScroll(SearchScrollRequest request, ActionListener<SearchResponse> listener);

    /**
     * A terms request  to get terms in one or more indices of specific fields and their
     * document frequencies (in how many document each term exists).
     *
     * @param request The term request
     * @return The result future
     * @see Requests#termsRequest(String...)
     */
    ActionFuture<TermsResponse> terms(TermsRequest request);

    /**
     * A terms request  to get terms in one or more indices of specific fields and their
     * document frequencies (in how many document each term exists).
     *
     * @param request  The term request
     * @param listener A listener to be notified of the result
     * @see Requests#termsRequest(String...)
     */
    void terms(TermsRequest request, ActionListener<TermsResponse> listener);

    /**
     * A more like this action to search for documents that are "like" a specific document.
     *
     * @param request The more like this request
     * @return The response future
     */
    ActionFuture<SearchResponse> moreLikeThis(MoreLikeThisRequest request);

    /**
     * A more like this action to search for documents that are "like" a specific document.
     *
     * @param request  The more like this request
     * @param listener A listener to be notified of the result
     */
    void moreLikeThis(MoreLikeThisRequest request, ActionListener<SearchResponse> listener);
}