# 原始组件 Props 对比

## lottie-web 原始 Props

### 基础配置 (loadAnimation 方法参数)

```typescript
interface AnimationConfig {
  // === 必需参数 ===
  container: Element | string;          // DOM 容器元素或选择器
  
  // === 动画数据 ===
  animationData?: any;                  // 动画 JSON 数据对象
  path?: string;                        // 动画 JSON 文件路径
  
  // === 渲染配置 ===
  renderer?: 'svg' | 'canvas' | 'html'; // 渲染器类型，默认 'svg'
  
  // === 播放控制 ===
  loop?: boolean | number;              // 循环播放，true/false 或循环次数
  autoplay?: boolean;                   // 自动播放，默认 true
  
  // === 实例管理 ===
  name?: string;                        // 动画实例名称
  
  // === 渲染器设置 ===
  rendererSettings?: {
    // SVG 渲染器设置
    preserveAspectRatio?: string;       // SVG preserveAspectRatio 属性
    progressiveLoad?: boolean;          // 渐进式加载，默认 false
    hideOnTransparent?: boolean;        // 透明时隐藏，默认 true
    className?: string;                 // CSS 类名
    
    // Canvas 渲染器设置
    context?: CanvasRenderingContext2D; // Canvas 上下文
    clearCanvas?: boolean;              // 清除画布，默认 true
    
    // HTML 渲染器设置
    // (HTML 渲染器较少使用，配置项相对简单)
  };
  
  // === 高级配置 ===
  initialSegment?: [number, number];    // 初始播放段
  assetsPath?: string;                  // 资源路径前缀
}
```

### 动画实例方法 (AnimationItem)

```typescript
interface AnimationItem {
  // === 播放控制 ===
  play(): void;                         // 播放动画
  pause(): void;                        // 暂停动画
  stop(): void;                         // 停止动画
  
  // === 跳转控制 ===
  goToAndStop(frame: number, isFrame?: boolean): void;  // 跳转并停止
  goToAndPlay(frame: number, isFrame?: boolean): void;  // 跳转并播放
  
  // === 播放设置 ===
  setSpeed(speed: number): void;        // 设置播放速度
  setDirection(direction: 1 | -1): void; // 设置播放方向
  
  // === 段播放 ===
  playSegments(segments: [number, number] | [number, number][], forceFlag?: boolean): void;
  
  // === 状态获取 ===
  getDuration(inFrames?: boolean): number;     // 获取总时长
  getCurrentTime(): number;                    // 获取当前时间
  getTotalFrames(): number;                    // 获取总帧数
  isPaused: boolean;                          // 是否暂停状态
  
  // === 事件管理 ===
  addEventListener(type: string, callback: Function): void;
  removeEventListener(type: string, callback: Function): void;
  
  // === 资源管理 ===
  destroy(): void;                      // 销毁动画实例
  resize(): void;                       // 重新调整大小
}
```

### 支持的事件类型

```typescript
type LottieWebEvents = 
  | 'complete'          // 动画播放完成
  | 'loopComplete'      // 单次循环完成
  | 'enterFrame'        // 进入新帧
  | 'segmentStart'      // 播放段开始
  | 'config_ready'      // 配置就绪
  | 'data_ready'        // 数据加载完成
  | 'loaded_images'     // 图片加载完成
  | 'DOMLoaded'         // DOM 加载完成
  | 'destroy';          // 实例销毁
```

## lottie-miniprogram 原始 Props

### 初始化方法

```typescript
// 必须在使用前调用
lottie.setup(canvas: WechatMiniprogram.Canvas): void;
```

### 基础配置 (loadAnimation 方法参数)

```typescript
interface MiniprogramAnimationConfig {
  // === 动画数据 ===
  animationData?: any;                  // 动画 JSON 数据对象
  path?: string;                        // 动画 JSON 文件路径（网络地址）
  
  // === 播放控制 ===
  loop?: boolean;                       // 循环播放，默认 true
  autoplay?: boolean;                   // 自动播放，默认 true
  
  // === 渲染设置（必需） ===
  rendererSettings: {
    context: WechatMiniprogram.CanvasRenderingContext2D; // Canvas 上下文（必填）
    clearCanvas?: boolean;              // 清除画布，默认 true
  };
  
  // === 可选配置 ===
  name?: string;                        // 动画实例名称
}
```

### 动画实例方法

```typescript
interface MiniprogramAnimationItem {
  // === 播放控制 ===
  play(): void;                         // 播放动画
  pause(): void;                        // 暂停动画
  stop(): void;                         // 停止动画
  
  // === 跳转控制 ===
  goToAndStop(frame: number, isFrame?: boolean): void;
  goToAndPlay(frame: number, isFrame?: boolean): void;
  
  // === 播放设置 ===
  setSpeed(speed: number): void;        // 设置播放速度
  setDirection(direction: 1 | -1): void; // 设置播放方向
  
  // === 段播放 ===
  playSegments(segments: [number, number], forceFlag?: boolean): void;
  
  // === 状态获取 ===
  getDuration(inFrames?: boolean): number;
  getCurrentTime(): number;
  getTotalFrames(): number;
  isPaused: boolean;
  
  // === 事件管理 ===
  addEventListener(type: string, callback: Function): void;
  removeEventListener(type: string, callback: Function): void;
  
  // === 资源管理 ===
  destroy(): void;                      // 销毁动画实例
}
```

### 支持的事件类型

```typescript
type MiniprogramLottieEvents = 
  | 'complete'          // 动画播放完成
  | 'loopComplete'      // 单次循环完成
  | 'enterFrame'        // 进入新帧
  | 'segmentStart'      // 播放段开始
  | 'data_ready'        // 数据加载完成
  | 'destroy';          // 实例销毁
```

## 主要差异对比

| 功能/配置 | lottie-web | lottie-miniprogram | 说明 |
|----------|------------|-------------------|------|
| **容器** | `container` (必需) | 通过 `setup(canvas)` | Web需要DOM容器，小程序需要Canvas |
| **渲染器** | `svg`/`canvas`/`html` | 仅 `canvas` | 小程序只支持Canvas渲染 |
| **上下文** | 可选 | 必需 | 小程序必须提供Canvas上下文 |
| **循环次数** | 支持数字 | 仅布尔值 | Web支持指定循环次数 |
| **资源路径** | 支持相对/绝对路径 | 仅网络地址 | 小程序限制本地文件访问 |
| **HTML渲染** | 支持 | 不支持 | 小程序无DOM环境 |
| **表达式** | 支持 | 不支持 | 小程序不支持动态脚本执行 |

## 使用示例对比

### lottie-web 使用示例

```javascript
// 基础使用
const animation = lottie.loadAnimation({
  container: document.getElementById('lottie-container'),
  renderer: 'svg',
  loop: true,
  autoplay: true,
  path: '/path/to/animation.json'
});

// 高级配置
const advancedAnimation = lottie.loadAnimation({
  container: document.getElementById('advanced-container'),
  renderer: 'canvas',
  loop: 3,
  autoplay: false,
  animationData: animationJsonData,
  rendererSettings: {
    clearCanvas: true,
    context: canvasContext,
    preserveAspectRatio: 'xMidYMid meet'
  }
});

// 事件监听
animation.addEventListener('complete', () => {
  console.log('动画播放完成');
});

// 控制方法
animation.play();
animation.setSpeed(0.5);
animation.goToAndStop(30, true);
```

### lottie-miniprogram 使用示例

```javascript
// 小程序页面
Page({
  onReady() {
    // 获取Canvas实例
    const query = this.createSelectorQuery();
    query.select('#lottie-canvas')
      .fields({ node: true, size: true })
      .exec((res) => {
        const canvas = res[0].node;
        const context = canvas.getContext('2d');
        
        // 初始化
        lottie.setup(canvas);
        
        // 加载动画
        this.animation = lottie.loadAnimation({
          loop: true,
          autoplay: true,
          path: 'https://example.com/animation.json',
          rendererSettings: {
            context: context,
            clearCanvas: true
          }
        });
        
        // 事件监听
        this.animation.addEventListener('complete', () => {
          console.log('动画播放完成');
        });
      });
  },
  
  // 控制方法
  playAnimation() {
    this.animation.play();
  },
  
  pauseAnimation() {
    this.animation.pause();
  }
});
```

## 关键限制和注意事项

### lottie-web 限制
- HTML渲染器性能较差，不推荐复杂动画使用
- Canvas渲染器需要手动管理设备像素比
- 大型动画可能影响页面性能

### lottie-miniprogram 限制
- 必须使用Canvas渲染，无其他选择
- 不支持Lottie表达式功能
- 需要基础库版本 >= 2.8.0
- 网络动画文件需要配置域名白名单
- Canvas尺寸需要手动设置

这些原始组件的特性和限制，为我们设计通用组件提供了重要的参考依据。