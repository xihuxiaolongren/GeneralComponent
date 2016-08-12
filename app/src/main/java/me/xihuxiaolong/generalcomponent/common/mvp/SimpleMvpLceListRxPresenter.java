/*
 *  Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package me.xihuxiaolong.generalcomponent.common.mvp;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import rx.Subscriber;

/**
 * A presenter for RxJava, that assumes that only one Observable is subscribed by this presenter.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class SimpleMvpLceListRxPresenter<V extends IMvpLceListView<M>, M>
    extends MvpBasePresenter<V>
    implements MvpPresenter<V> {

  protected Subscriber<M> subscriberLoad;
  protected Subscriber<M> subscriberLoadMore;

  /**
   * Unsubscribes the subscriber and set it to null
   */
  protected void unsubscribe(Subscriber subscriber) {
    if (subscriber != null && !subscriber.isUnsubscribed()) {
      subscriber.unsubscribe();
    }
  }

  /**
   * Subscribes the presenter himself as subscriber on the observable
   *
   * @param pullToRefresh Pull to refresh?
   */
  public Subscriber<M> subscribe(final boolean pullToRefresh) {

    if (isViewAttached()) {
      getView().showLoading(pullToRefresh);
    }

    unsubscribe(subscriberLoad);

    subscriberLoad = new Subscriber<M>() {
      private boolean ptr = pullToRefresh;

      @Override public void onCompleted() {
        SimpleMvpLceListRxPresenter.this.onCompleted(ptr);
      }

      @Override public void onError(Throwable e) {
        SimpleMvpLceListRxPresenter.this.onError(e, ptr);
      }

      @Override public void onNext(M m) {
        SimpleMvpLceListRxPresenter.this.onNext(m);
      }
    };

    return subscriberLoad;
  }

  /**
   * Subscribes the presenter himself as subscriber on the observable
   */
  public Subscriber<M> subscribeLoadMore() {

    if (isViewAttached()) {
      getView().showLoadingMore();
    }

    unsubscribe(subscriberLoadMore);

    subscriberLoadMore = new Subscriber<M>() {

      @Override public void onCompleted() {
        SimpleMvpLceListRxPresenter.this.onLoadMoreCompleted();
      }

      @Override public void onError(Throwable e) {
        SimpleMvpLceListRxPresenter.this.onErrorLoadMore(e);
      }

      @Override public void onNext(M data) {
        SimpleMvpLceListRxPresenter.this.onLoadMoreNext(data);
      }
    };

    return subscriberLoadMore;
  }

  protected void onCompleted(boolean pullToRefresh) {
    if (isViewAttached()) {
      getView().showContent();
    }
    unsubscribe(subscriberLoad);
  }

  protected void onLoadMoreCompleted() {
    if (isViewAttached()) {
      getView().showMoreData();
    }
    unsubscribe(subscriberLoadMore);
  }

  protected void onError(Throwable e, boolean pullToRefresh) {
    if (isViewAttached()) {
      getView().showError(e, pullToRefresh);
    }
    unsubscribe(subscriberLoad);
  }

  protected void onNext(M data) {
    if (isViewAttached()) {
      getView().setData(data);
    }
  }

  protected void onLoadMoreNext(M data) {
    if (isViewAttached()) {
      getView().setMoreData(data);
    }
  }

  protected void onErrorLoadMore(Throwable e) {
    if (isViewAttached()) {
      getView().showLoadMoreError();;
    }
    unsubscribe(subscriberLoadMore);
  }

  @Override public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);
    if (!retainInstance) {
      unsubscribe(subscriberLoad);
      unsubscribe(subscriberLoadMore);
    }
  }
}
