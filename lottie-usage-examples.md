# 通用 Lottie 组件使用示例

## 基础使用

### 1. 使用动画数据对象

```tsx
import { UniversalLottieProps } from './universal-lottie-props';
import animationData from './animation.json';

const BasicLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    animationData,
    loop: true,
    autoplay: true,
    width: 400,
    height: 300,
  };

  return <LottieComponent {...lottieProps} />;
};
```

### 2. 使用动画文件路径

```tsx
const PathLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    path: 'https://example.com/animation.json',
    loop: false,
    autoplay: true,
    onComplete: () => console.log('动画播放完成'),
  };

  return <LottieComponent {...lottieProps} />;
};
```

## 高级配置示例

### 1. 自定义播放控制

```tsx
const AdvancedLottie: React.FC = () => {
  const [isPaused, setIsPaused] = useState(false);
  const [speed, setSpeed] = useState(1);

  const lottieProps: UniversalLottieProps = {
    animationData,
    loop: 3, // 循环 3 次
    speed,
    isPaused,
    direction: 1,
    segments: [0, 50], // 只播放前 50 帧
    onLoopComplete: () => console.log('循环完成'),
    onComplete: () => console.log('全部播放完成'),
  };

  return (
    <div>
      <LottieComponent {...lottieProps} />
      <button onClick={() => setIsPaused(!isPaused)}>
        {isPaused ? '播放' : '暂停'}
      </button>
      <input
        type="range"
        min="0.5"
        max="3"
        step="0.1"
        value={speed}
        onChange={(e) => setSpeed(Number(e.target.value))}
      />
    </div>
  );
};
```

### 2. 多渲染器支持

```tsx
// SVG 渲染器（默认，适合简单动画）
const SVGLottie: React.FC = () => (
  <LottieComponent
    animationData={animationData}
    renderer="svg"
    rendererSettings={{
      preserveAspectRatio: 'xMidYMid meet',
    }}
  />
);

// Canvas 渲染器（适合复杂动画）
const CanvasLottie: React.FC = () => (
  <LottieComponent
    animationData={animationData}
    renderer="canvas"
    rendererSettings={{
      clearCanvas: true,
      dpr: window.devicePixelRatio || 1,
    }}
  />
);

// HTML 渲染器（适合文本动画）
const HTMLLottie: React.FC = () => (
  <LottieComponent
    animationData={animationData}
    renderer="html"
    rendererSettings={{
      className: 'lottie-html-container',
    }}
  />
);
```

## 平台特定使用

### 1. Web 平台

```tsx
const WebLottie: React.FC = () => {
  const containerRef = useRef<HTMLDivElement>(null);

  const lottieProps: UniversalLottieProps = {
    animationData,
    web: {
      container: containerRef.current,
      worker: true, // 使用 Web Worker 提高性能
    },
    pauseOnInvisible: true, // 不可见时暂停
    priority: 'high',
  };

  return (
    <div ref={containerRef}>
      <LottieComponent {...lottieProps} />
    </div>
  );
};
```

### 2. 微信小程序

```tsx
// 小程序组件示例
Component({
  data: {
    lottieProps: {
      path: 'https://example.com/animation.json',
      loop: true,
      autoplay: true,
      renderer: 'canvas',
      miniprogram: {
        // canvas 实例会在 onReady 中设置
        canvas: null,
        componentContext: this,
      },
      rendererSettings: {
        context: null, // Canvas 上下文会在 onReady 中设置
        clearCanvas: true,
      },
    },
  },

  onReady() {
    const query = this.createSelectorQuery();
    query.select('#lottie-canvas')
      .fields({ node: true, size: true })
      .exec((res) => {
        const canvas = res[0].node;
        const ctx = canvas.getContext('2d');
        
        this.setData({
          'lottieProps.miniprogram.canvas': canvas,
          'lottieProps.rendererSettings.context': ctx,
        });
      });
  },
});
```

## 事件处理示例

### 1. 完整的事件监听

```tsx
const EventLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    animationData,
    onDataReady: () => console.log('数据加载完成'),
    onLoadComplete: () => console.log('动画加载完成'),
    onEnterFrame: ({ currentTime, totalTime }) => {
      console.log(`当前进度: ${(currentTime / totalTime * 100).toFixed(1)}%`);
    },
    onComplete: () => console.log('动画播放完成'),
    onLoopComplete: () => console.log('单次循环完成'),
    onError: (error) => console.error('动画错误:', error),
    onDestroy: () => console.log('动画实例已销毁'),
  };

  return <LottieComponent {...lottieProps} />;
};
```

### 2. 使用实例方法

```tsx
const InteractiveLottie: React.FC = () => {
  const lottieRef = useRef<LottieInstance>(null);

  const handlePlay = () => lottieRef.current?.play();
  const handlePause = () => lottieRef.current?.pause();
  const handleStop = () => lottieRef.current?.stop();
  const handleGoToFrame = (frame: number) => 
    lottieRef.current?.goToAndStop(frame);

  return (
    <div>
      <LottieComponent
        ref={lottieRef}
        animationData={animationData}
        autoplay={false}
      />
      <div>
        <button onClick={handlePlay}>播放</button>
        <button onClick={handlePause}>暂停</button>
        <button onClick={handleStop}>停止</button>
        <button onClick={() => handleGoToFrame(30)}>跳到第30帧</button>
      </div>
    </div>
  );
};
```

## 性能优化示例

### 1. 懒加载和资源优化

```tsx
const OptimizedLottie: React.FC = () => {
  const [isVisible, setIsVisible] = useState(false);

  const lottieProps: UniversalLottieProps = {
    path: 'https://example.com/large-animation.json',
    preloadAssets: true, // 预加载资源
    priority: 'low', // 低优先级
    pauseOnInvisible: true, // 不可见时暂停
    frameRate: 24, // 限制帧率
    rendererSettings: {
      progressiveLoad: true, // 渐进式加载
    },
  };

  return (
    <div>
      {isVisible && <LottieComponent {...lottieProps} />}
      <button onClick={() => setIsVisible(!isVisible)}>
        {isVisible ? '隐藏' : '显示'} 动画
      </button>
    </div>
  );
};
```

### 2. 多实例管理

```tsx
const MultiLottie: React.FC = () => {
  const animations = [
    { name: 'loading', path: '/loading.json', priority: 'high' },
    { name: 'success', path: '/success.json', priority: 'normal' },
    { name: 'error', path: '/error.json', priority: 'normal' },
  ];

  return (
    <div>
      {animations.map((anim) => (
        <LottieComponent
          key={anim.name}
          name={anim.name}
          path={anim.path}
          priority={anim.priority}
          pauseOnInvisible={true}
        />
      ))}
    </div>
  );
};
```

## 样式和布局

### 1. 响应式设计

```tsx
const ResponsiveLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    animationData,
    style: {
      width: '100%',
      height: 'auto',
      maxWidth: '500px',
    },
    className: 'responsive-lottie',
  };

  return <LottieComponent {...lottieProps} />;
};
```

### 2. 自定义容器

```tsx
const CustomContainerLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    animationData,
    id: 'custom-lottie',
    className: 'custom-lottie-container',
    style: {
      border: '2px solid #007bff',
      borderRadius: '10px',
      padding: '20px',
      background: 'linear-gradient(45deg, #f0f0f0, #ffffff)',
    },
    width: 300,
    height: 300,
  };

  return <LottieComponent {...lottieProps} />;
};
```

## 调试和开发

### 1. 开发模式

```tsx
const DebugLottie: React.FC = () => {
  const lottieProps: UniversalLottieProps = {
    animationData,
    debug: true, // 启用调试模式
    onEnterFrame: ({ currentTime, totalTime }) => {
      if (process.env.NODE_ENV === 'development') {
        console.log('Frame:', currentTime, '/', totalTime);
      }
    },
    onError: (error) => {
      console.error('Lottie Error:', error);
      // 可以在这里添加错误上报逻辑
    },
  };

  return <LottieComponent {...lottieProps} />;
};
```

### 2. 错误边界

```tsx
class LottieErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error) {
    return { hasError: true };
  }

  componentDidCatch(error, errorInfo) {
    console.error('Lottie component error:', error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return <div>动画加载失败，请刷新重试</div>;
    }

    return this.props.children;
  }
}

const SafeLottie: React.FC = () => (
  <LottieErrorBoundary>
    <LottieComponent
      animationData={animationData}
      onError={(error) => console.error('Animation error:', error)}
    />
  </LottieErrorBoundary>
);
```

## 最佳实践

1. **选择合适的渲染器**：
   - SVG：适合简单图标和小动画
   - Canvas：适合复杂动画和高性能需求
   - HTML：适合文本动画和DOM操作

2. **性能优化**：
   - 使用 `pauseOnInvisible` 暂停不可见动画
   - 设置合适的 `priority` 优先级
   - 限制 `frameRate` 降低CPU使用

3. **资源管理**：
   - 大型动画使用 `preloadAssets`
   - 及时调用 `destroy()` 清理资源
   - 使用 `progressiveLoad` 改善加载体验

4. **平台适配**：
   - Web端优先使用SVG渲染器
   - 小程序必须使用Canvas渲染器
   - 根据平台特性调整配置参数