关于 BaseActivity、BaseFragment、BaseViewHolder等基类与ViewBind的结合写法

1、在BaseActivity中，给基类添加泛型，即ViewBinding的子类。
    定义一个泛型的ViewBinding的成员变量。
    在OnCreate方法中，进行给该成员变量赋值。
    伪代码如下：

    class BaseActivity<T extends ViewBinding>
        protected T binding;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = getBinding();
            setContentView(binding.getRoot());
            init();
        }
    
        // 获取binding
        protected T getBinding(){
            try {
                Type superClass = getClass().getGenericSuperclass();
                Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
                Class<?> clazz = ClassUtils.getRawType(type);
                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                return (T) method.invoke(null, getLayoutInflater());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

2、在BaseFragment中，给基类添加泛型，即也是ViewBinding的子类。
    定义一个泛型的ViewBinding的成员变量。
    在OnCreateView方法中，进行给该成员变量赋值。
    代码如下：
    
    class BaseFragment<T extends ViewBinding> extends Fragment {
        protected T binding;
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            binding = getBinding(container);
            init();
            return binding.getRoot();
        }

        // 获取binding
        protected T getBinding(ViewGroup container){
            try {
                Type superClass = getClass().getGenericSuperclass();
                Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
                Class<?> clazz = ClassUtils.getRawType(type);
                Method method = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                return (T) method.invoke(null, getLayoutInflater(), container, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

3、在BaseViewHolder中，更加简单，同样的给基类添加泛型，也是ViewBinding的子类。
    定义一个泛型的ViewBinding的成员变量。
    在构造方法中给该变量赋值。
    注意，该基类中必须添加成员变量binding的get方法。
    代码如下：
    
    class BaseRvViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    
        protected T binding;
    
        public BaseRvViewHolder(@NonNull T viewBinding) {
            super(viewBinding.getRoot());
            this.binding = viewBinding;
        }
    
        public void setBinding(T binding) {
            this.binding = binding;
        }
    
        public T getBinding() {
            return this.binding;
        }
    }    
4、在自定义View中，如何使用ViewBinding与自定义View相结合呢。    
    