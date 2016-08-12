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
public abstract class SimpleMvpLceListPresenter<V extends IMvpLceListView<M>, M>
    extends MvpBasePresenter<V>
    implements MvpPresenter<V> {

  protected void onCompleted(boolean pullToRefresh) {
    if (isViewAttached()) {
      getView().showContent();
    }
  }

  protected void onLoadMoreCompleted() {
    if (isViewAttached()) {
      getView().showMoreData();
    }
  }

  protected void onError(Throwable e, boolean pullToRefresh) {
    if (isViewAttached()) {
      getView().showError(e, pullToRefresh);
    }
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
  }

  @Override public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);
    if (!retainInstance) {
    }
  }
}
