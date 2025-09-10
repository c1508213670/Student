/**
 * 通用 Lottie 组件 Props 接口设计
 * 基于 lottie-web 和 lottie-miniprogram 的 API 设计
 */

// 渲染器类型
export type RendererType = 'svg' | 'canvas' | 'html';

// 播放方向
export type PlayDirection = 1 | -1;

// 动画数据类型
export interface AnimationData {
  [key: string]: any;
}

// 播放段配置
export interface PlaySegment {
  start: number;
  end: number;
}

// 渲染器设置
export interface RendererSettings {
  // 通用设置
  context?: any; // Canvas 上下文（小程序必需）
  clearCanvas?: boolean; // 是否在渲染前清除画布
  progressiveLoad?: boolean; // 是否逐帧加载
  hideOnTransparent?: boolean; // 透明时是否隐藏
  
  // SVG 渲染器设置
  preserveAspectRatio?: string; // SVG 视口对齐方式
  
  // Canvas 渲染器设置
  dpr?: number; // 设备像素比
  
  // HTML 渲染器设置
  className?: string; // CSS 类名
}

// 事件回调类型
export interface LottieEventCallbacks {
  onComplete?: () => void; // 动画完成回调
  onLoopComplete?: () => void; // 循环完成回调
  onEnterFrame?: (event: { currentTime: number; totalTime: number }) => void; // 帧更新回调
  onSegmentStart?: (event: { firstFrame: number; totalFrames: number }) => void; // 段开始回调
  onError?: (error: Error) => void; // 错误回调
  onDataReady?: () => void; // 数据加载完成回调
  onLoadComplete?: () => void; // 动画加载完成回调
  onDestroy?: () => void; // 销毁回调
}

// 主要的 Props 接口
export interface UniversalLottieProps extends LottieEventCallbacks {
  // === 基础配置 ===
  
  /**
   * 动画数据 JSON 对象
   * 与 path 二选一，优先级高于 path
   */
  animationData?: AnimationData;
  
  /**
   * 动画 JSON 文件路径/URL
   * 与 animationData 二选一
   */
  path?: string;
  
  /**
   * 动画名称，用于多实例管理
   */
  name?: string;
  
  // === 播放控制 ===
  
  /**
   * 是否循环播放
   * @default true
   */
  loop?: boolean | number; // boolean 或循环次数
  
  /**
   * 是否自动播放
   * @default true
   */
  autoplay?: boolean;
  
  /**
   * 播放速度倍率
   * @default 1
   */
  speed?: number;
  
  /**
   * 播放方向
   * 1: 正向播放, -1: 反向播放
   * @default 1
   */
  direction?: PlayDirection;
  
  /**
   * 初始播放进度 (0-1)
   * @default 0
   */
  initialProgress?: number;
  
  /**
   * 播放段配置
   */
  segments?: PlaySegment[] | [number, number];
  
  /**
   * 是否强制播放段
   * @default false
   */
  forceSegments?: boolean;
  
  // === 渲染配置 ===
  
  /**
   * 渲染器类型
   * @default 'svg'
   */
  renderer?: RendererType;
  
  /**
   * 渲染器设置
   */
  rendererSettings?: RendererSettings;
  
  /**
   * 容器宽度
   */
  width?: number;
  
  /**
   * 容器高度
   */
  height?: number;
  
  /**
   * 资源路径前缀
   * 用于动画中引用外部资源时的路径拼接
   */
  assetsPath?: string;
  
  /**
   * 是否启用子帧渲染（提高流畅度）
   * @default true
   */
  subframe?: boolean;
  
  // === 状态控制 ===
  
  /**
   * 是否暂停
   * @default false
   */
  isPaused?: boolean;
  
  /**
   * 是否停止
   * @default false
   */
  isStopped?: boolean;
  
  // === 样式和布局 ===
  
  /**
   * CSS 类名
   */
  className?: string;
  
  /**
   * 内联样式
   */
  style?: React.CSSProperties | Record<string, any>;
  
  /**
   * 容器 ID
   */
  id?: string;
  
  // === 高级配置 ===
  
  /**
   * 是否预加载所有资源
   * @default false
   */
  preloadAssets?: boolean;
  
  /**
   * 动画优先级（用于性能优化）
   * @default 'normal'
   */
  priority?: 'low' | 'normal' | 'high';
  
  /**
   * 是否在不可见时暂停动画
   * @default true
   */
  pauseOnInvisible?: boolean;
  
  /**
   * 帧率限制
   */
  frameRate?: number;
  
  /**
   * 是否启用调试模式
   * @default false
   */
  debug?: boolean;
  
  // === 平台特定配置 ===
  
  /**
   * 小程序平台特定配置
   */
  miniprogram?: {
    canvas?: any; // 小程序 canvas 实例
    componentContext?: any; // 小程序组件上下文
  };
  
  /**
   * Web 平台特定配置
   */
  web?: {
    container?: HTMLElement | string; // DOM 容器
    worker?: boolean; // 是否使用 Web Worker
  };
}

// 扩展的事件类型
export interface LottieEvents {
  complete: () => void;
  loopComplete: () => void;
  enterFrame: (event: { currentTime: number; totalTime: number }) => void;
  segmentStart: (event: { firstFrame: number; totalFrames: number }) => void;
  error: (error: Error) => void;
  dataReady: () => void;
  loadComplete: () => void;
  destroy: () => void;
  play: () => void;
  pause: () => void;
  stop: () => void;
}

// 组件实例方法接口
export interface LottieInstance {
  // 播放控制
  play(): void;
  pause(): void;
  stop(): void;
  goToAndStop(frame: number, isFrame?: boolean): void;
  goToAndPlay(frame: number, isFrame?: boolean): void;
  setDirection(direction: PlayDirection): void;
  setSpeed(speed: number): void;
  
  // 段播放
  playSegments(segments: PlaySegment[] | [number, number], forceFlag?: boolean): void;
  
  // 状态获取
  getCurrentTime(): number;
  getTotalFrames(): number;
  getDuration(): number;
  getProgress(): number;
  isPaused(): boolean;
  
  // 资源管理
  destroy(): void;
  resize(): void;
  
  // 事件管理
  addEventListener<K extends keyof LottieEvents>(
    type: K,
    listener: LottieEvents[K]
  ): void;
  removeEventListener<K extends keyof LottieEvents>(
    type: K,
    listener: LottieEvents[K]
  ): void;
}

// 默认配置
export const DEFAULT_LOTTIE_PROPS: Partial<UniversalLottieProps> = {
  loop: true,
  autoplay: true,
  speed: 1,
  direction: 1,
  renderer: 'svg',
  initialProgress: 0,
  subframe: true,
  isPaused: false,
  isStopped: false,
  preloadAssets: false,
  priority: 'normal',
  pauseOnInvisible: true,
  debug: false,
  forceSegments: false,
  rendererSettings: {
    clearCanvas: true,
    progressiveLoad: false,
    hideOnTransparent: true,
  },
};