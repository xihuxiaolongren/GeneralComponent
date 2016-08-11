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
public abstract class SimpleMvpLceListRxPresenter<V extends IMvpLceListView<M, D>, M, D>
    extends MvpBasePresenter<V>
    implements MvpPresenter<V> {

  protected Subscriber<M> subscriberLoad;
  protected Subscriber<D> subscriberLoadMore;

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
  public Subscriber<D> subscribeLoadMore() {

    if (isViewAttached()) {
      getView().showLoadingMore();
    }

    unsubscribe(subscriberLoadMore);

    subscriberLoadMore = new Subscriber<D>() {

      @Override public void onCompleted() {
        SimpleMvpLceListRxPresenter.this.onLoadMoreCompleted();
      }

      @Override public void onError(Throwable e) {
        SimpleMvpLceListRxPresenter.this.onErrorLoadMore(e);
      }

      @Override public void onNext(D d) {
        SimpleMvpLceListRxPresenter.this.onLoadMoreNext(d);
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

  protected void onLoadMoreNext(D d) {
    if (isViewAttached()) {
      getView().setMoreData(d);
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
