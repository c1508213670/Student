# 通用 Lottie 组件 Props 设计说明

## 设计目标

基于对 `lottie-web` 和 `lottie-miniprogram` 两个组件的深入研究，设计一个统一的、可扩展的、跨平台的 Lottie 组件 Props 接口。

## 核心设计原则

### 1. **兼容性优先**
- 覆盖两个原始组件的所有核心功能
- 保持向后兼容，便于从现有组件迁移
- 支持渐进式采用，可以只使用基础功能

### 2. **平台无关性**
- 通过 `miniprogram` 和 `web` 配置项处理平台差异
- 核心 API 保持一致，平台特定功能通过扩展实现
- 智能默认值，减少平台切换时的配置工作

### 3. **类型安全**
- 完整的 TypeScript 类型定义
- 明确的枚举类型和联合类型
- 详细的 JSDoc 注释

### 4. **性能考虑**
- 提供性能优化相关的配置项
- 支持懒加载和资源管理
- 可配置的优先级和帧率限制

## 主要功能模块

### 1. 基础配置模块
```typescript
{
  animationData?: AnimationData;  // 动画数据
  path?: string;                  // 动画文件路径
  name?: string;                  // 实例名称
}
```

**设计考虑**：
- `animationData` 和 `path` 二选一，优先级：`animationData` > `path`
- `name` 用于多实例管理，在复杂应用中很有用

### 2. 播放控制模块
```typescript
{
  loop?: boolean | number;        // 循环播放
  autoplay?: boolean;             // 自动播放
  speed?: number;                 // 播放速度
  direction?: PlayDirection;      // 播放方向
  initialProgress?: number;       // 初始进度
  segments?: PlaySegment[] | [number, number]; // 播放段
}
```

**设计考虑**：
- `loop` 支持布尔值和数字，数字表示循环次数
- `segments` 支持多种格式，兼容不同的使用习惯
- 所有控制参数都有合理的默认值

### 3. 渲染配置模块
```typescript
{
  renderer?: RendererType;        // 渲染器类型
  rendererSettings?: RendererSettings; // 渲染器设置
  width?: number;                 // 宽度
  height?: number;                // 高度
  assetsPath?: string;           // 资源路径
}
```

**设计考虑**：
- `renderer` 支持 SVG、Canvas、HTML 三种类型
- `rendererSettings` 包含所有渲染器的配置选项
- 尺寸配置独立，便于响应式设计

### 4. 事件系统模块
```typescript
{
  onComplete?: () => void;
  onLoopComplete?: () => void;
  onEnterFrame?: (event) => void;
  onError?: (error: Error) => void;
  // ... 更多事件
}
```

**设计考虑**：
- 涵盖动画生命周期的所有关键事件
- 事件参数类型化，提供有用的上下文信息
- 错误处理完善，便于调试和监控

### 5. 平台特定模块
```typescript
{
  miniprogram?: {
    canvas?: any;
    componentContext?: any;
  };
  web?: {
    container?: HTMLElement | string;
    worker?: boolean;
  };
}
```

**设计考虑**：
- 将平台差异封装在独立的配置对象中
- 避免核心 API 被平台特定代码污染
- 便于未来扩展到其他平台

## 与原始组件的对比

### lottie-web 兼容性

| lottie-web 参数 | 通用组件对应 | 说明 |
|----------------|-------------|------|
| `animationData` | `animationData` | 完全兼容 |
| `path` | `path` | 完全兼容 |
| `loop` | `loop` | 增强支持数字 |
| `autoplay` | `autoplay` | 完全兼容 |
| `name` | `name` | 完全兼容 |
| `renderer` | `renderer` | 完全兼容 |
| `container` | `web.container` | 平台特定 |
| `rendererSettings` | `rendererSettings` | 扩展支持 |

### lottie-miniprogram 兼容性

| lottie-miniprogram 参数 | 通用组件对应 | 说明 |
|------------------------|-------------|------|
| `path` | `path` | 完全兼容 |
| `loop` | `loop` | 完全兼容 |
| `autoplay` | `autoplay` | 完全兼容 |
| `speed` | `speed` | 完全兼容 |
| `direction` | `direction` | 完全兼容 |
| `rendererSettings.context` | `rendererSettings.context` | 完全兼容 |

## 扩展性设计

### 1. 新平台支持
可以通过添加新的平台配置对象来支持新平台：
```typescript
{
  reactNative?: {
    // React Native 特定配置
  };
  flutter?: {
    // Flutter 特定配置
  };
}
```

### 2. 新功能扩展
通过扩展现有接口来添加新功能：
```typescript
interface ExtendedLottieProps extends UniversalLottieProps {
  // 新功能配置
  advancedFeature?: AdvancedConfig;
}
```

### 3. 自定义渲染器
支持自定义渲染器类型：
```typescript
type RendererType = 'svg' | 'canvas' | 'html' | string;
```

## 最佳实践建议

### 1. 基础使用
对于简单场景，只需要提供必要的配置：
```typescript
<LottieComponent animationData={data} />
```

### 2. 性能优化
对于复杂应用，建议使用性能相关配置：
```typescript
<LottieComponent
  animationData={data}
  priority="high"
  pauseOnInvisible={true}
  frameRate={30}
/>
```

### 3. 平台适配
根据目标平台选择合适的配置：
```typescript
// Web 平台
<LottieComponent renderer="svg" web={{ worker: true }} />

// 小程序平台
<LottieComponent renderer="canvas" miniprogram={{ canvas }} />
```

## 实现建议

### 1. 组件架构
```
UniversalLottieComponent
├── PlatformAdapter (平台适配层)
├── ConfigNormalizer (配置标准化)
├── EventManager (事件管理)
├── PerformanceManager (性能管理)
└── ErrorBoundary (错误边界)
```

### 2. 配置处理流程
```
用户配置 → 默认值合并 → 平台适配 → 参数验证 → 组件渲染
```

### 3. 错误处理策略
- 配置错误：提供警告并使用默认值
- 加载错误：触发 `onError` 回调
- 渲染错误：降级到备用渲染器

这个设计方案既保持了与原始组件的兼容性，又提供了更好的类型安全、性能优化和扩展性，是一个平衡各方面需求的通用解决方案。