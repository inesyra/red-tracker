package com.geovra.red.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.geovra.red.dashboard.viewmodel.DashboardViewModel;

import java.util.HashMap;
import java.util.Map;

public class ViewModelSingletonFactory extends ViewModelProvider.NewInstanceFactory {
  private DashboardViewModel vm;
  private static ViewModelSingletonFactory factory;
  private static final Map<Class<? extends ViewModel>, ViewModel> mHash = new HashMap<>();

  private ViewModelSingletonFactory() {}


  public ViewModelSingletonFactory(DashboardViewModel vm) {
    this.vm = vm;
  }


  @NonNull
  @Override
  public <T extends ViewModel> T create(final @NonNull Class<T> modelClass)
  {
    // mHash.put(modelClass, vm);

    if (DashboardViewModel.class.isAssignableFrom(modelClass)) {
      DashboardViewModel sharedVM = null;

      if (mHash.containsKey(modelClass)) {
        sharedVM = (DashboardViewModel) mHash.get(modelClass);
      } else {
        try {

          // sharedVM = (DashboardViewModel) modelClass.getConstructor(Runnable.class).newInstance(new Runnable() {
          //   @Override
          //   public void run() {
          //     mHash.remove(modelClass);
          //   }
          // });
          if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            sharedVM = DashboardViewModel.getInstance();
          } else {
            throw new IllegalArgumentException("Unknown view model " + modelClass);
          }

        } catch (Exception e) {
          throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }

        mHash.put(modelClass, sharedVM);
      }

      return (T) sharedVM;
    }

    return super.create(modelClass);
  }


  public static ViewModelSingletonFactory getInstance()
  {
    if (null == factory) {
      factory = new ViewModelSingletonFactory();
    }
    return factory;
  }
}